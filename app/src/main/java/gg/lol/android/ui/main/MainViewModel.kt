package gg.lol.android.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.domain.interactor.GetChampionsUseCase
import gg.op.lol.domain.interactor.GetItemUseCase
import gg.op.lol.domain.interactor.GetLatestVersionUseCase
import gg.op.lol.domain.interactor.GetRuneUseCase
import gg.op.lol.domain.interactor.GetSpellUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MainViewModel @Inject internal constructor(
    private val getLatestVersionUseCase: GetLatestVersionUseCase,
    private val getChampionsUseCase: GetChampionsUseCase,
    private val getSpellUseCase: GetSpellUseCase,
    private val getRuneUseCase: GetRuneUseCase,
    private val getItemUseCase: GetItemUseCase,
    private val preferencesHelper: PreferencesHelper
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val uiState: StateFlow<UiState<Unit>> get() = _uiState

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UiState.Error(exception)
    }

    init {
        getLatestVersion()
    }

    private fun load(latestVersion: String) {
        launchCoroutineIO {
            val currentVersion = preferencesHelper.currentVersion
            val versionPair = Pair(currentVersion, latestVersion)
            val championResponse = async { getChampionsUseCase.invoke(versionPair) }
            val runeResponse = async { getRuneUseCase.invoke(versionPair) }
            val itemResponse = async { getItemUseCase.invoke(versionPair) }
            val spellResponse = async { getSpellUseCase.invoke(versionPair) }
            awaitAll(championResponse, runeResponse, itemResponse, spellResponse)
            preferencesHelper.currentVersion = latestVersion
        }
    }

    private fun getLatestVersion() {
        launchCoroutineIO {
            getLatestVersionUseCase.invoke(Unit).collect { latestVersion ->
                load(latestVersion)
            }
        }
    }
}
