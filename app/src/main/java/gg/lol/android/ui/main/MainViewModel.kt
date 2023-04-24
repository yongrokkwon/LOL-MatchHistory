package gg.lol.android.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.BuildConfig
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.domain.interactor.DeleteGameDataUseCase
import gg.op.lol.domain.interactor.GetChampionsUseCase
import gg.op.lol.domain.interactor.GetItemUseCase
import gg.op.lol.domain.interactor.GetLatestVersionBaseUseCase
import gg.op.lol.domain.interactor.GetRuneUseCase
import gg.op.lol.domain.interactor.GetSpellUseCase
import gg.op.lol.domain.interactor.InsertGameDataUseCase
import gg.op.lol.domain.models.ChampionRuneItemSpell
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MainViewModel @Inject internal constructor(
    private val getLatestVersionUseCase: GetLatestVersionBaseUseCase,
    private val deleteGameDataBaseUseCase: DeleteGameDataUseCase,
    private val insertBaseDataBaseUseCase: InsertGameDataUseCase,
    private val getChampionsUseCase: GetChampionsUseCase,
    private val getSpellUseCase: GetSpellUseCase,
    private val getRuneUseCase: GetRuneUseCase,
    private val getItemUseCase: GetItemUseCase,
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
