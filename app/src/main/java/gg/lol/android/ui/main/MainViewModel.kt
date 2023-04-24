package gg.lol.android.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.BuildConfig
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.domain.interactor.DeleteGameDataBaseUseCase
import gg.op.lol.domain.interactor.GetChampionsBaseUseCase
import gg.op.lol.domain.interactor.GetItemBaseUseCase
import gg.op.lol.domain.interactor.GetLatestVersionBaseUseCase
import gg.op.lol.domain.interactor.GetRuneBaseUseCase
import gg.op.lol.domain.interactor.GetSpellBaseUseCase
import gg.op.lol.domain.interactor.InsertGameDataBaseUseCase
import gg.op.lol.domain.models.ChampionRuneItemSpell
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MainViewModel @Inject internal constructor(
    private val getLatestVersionUseCase: GetLatestVersionBaseUseCase,
    private val deleteGameDataBaseUseCase: DeleteGameDataBaseUseCase,
    private val insertBaseDataBaseUseCase: InsertGameDataBaseUseCase,
    private val getChampionsUseCase: GetChampionsBaseUseCase,
    private val getSpellUseCase: GetSpellBaseUseCase,
    private val getRuneUseCase: GetRuneBaseUseCase,
    private val getItemUseCase: GetItemBaseUseCase,
    private val preferencesHelper: PreferencesHelper
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val uiState: StateFlow<UiState<Unit>> get() = _uiState

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (BuildConfig.DEBUG) throwable.printStackTrace()
        _uiState.value = UiState.Error(throwable)
    }

    init {
        loadGameDataWithLatestApiVersion()
    }

    private fun loadGameDataFromApi(lolApiLatestVersion: String) {
        launchCoroutineIO {
            val lolApiCurrentVersion = preferencesHelper.lolApiVersion
            val versionPair = Pair(lolApiCurrentVersion, lolApiLatestVersion)
            val championResponse = async { getChampionsUseCase.invoke(versionPair) }
            val runeResponse = async { getRuneUseCase.invoke(versionPair) }
            val itemResponse = async { getItemUseCase.invoke(versionPair) }
            val spellResponse = async { getSpellUseCase.invoke(versionPair) }

            deleteGameDataBaseUseCase.invoke()
            insertBaseDataBaseUseCase.invoke(
                ChampionRuneItemSpell(
                    championResponse.await(),
                    runeResponse.await(),
                    itemResponse.await(),
                    spellResponse.await()
                )
            )
            preferencesHelper.lolApiVersion = lolApiLatestVersion
        }
    }

    private fun loadGameDataWithLatestApiVersion() {
        launchCoroutineIO {
            getLatestVersionUseCase.invoke().collect { lolApiLatestVersion ->
                loadGameDataFromApi(lolApiLatestVersion)
            }
        }
    }
}
