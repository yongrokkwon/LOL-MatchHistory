package gg.lol.android.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.domain.interactor.GetChampionsUseCase
import gg.op.lol.domain.interactor.GetFavoritesUseCase
import gg.op.lol.domain.interactor.GetItemUseCase
import gg.op.lol.domain.interactor.GetLatestVersionUseCase
import gg.op.lol.domain.interactor.GetRuneUseCase
import gg.op.lol.domain.interactor.GetSpellUseCase
import gg.op.lol.domain.interactor.UpdateFavoriteSummonerUseCase
import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

typealias MainViewModelUiState = UiState<List<SearchHistorySummonerJoin>>

@HiltViewModel
class MainViewModel @Inject internal constructor(
    private val getLatestVersionUseCase: GetLatestVersionUseCase,
    private val getChampionsUseCase: GetChampionsUseCase,
    private val getSpellUseCase: GetSpellUseCase,
    private val getRuneUseCase: GetRuneUseCase,
    private val getItemUseCase: GetItemUseCase,
    private val preferencesHelper: PreferencesHelper,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val updateFavoriteSummonerUseCase: UpdateFavoriteSummonerUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<MainViewModelUiState>(UiState.Loading)
    val uiState: StateFlow<MainViewModelUiState> get() = _uiState

    val latestVersion get() = preferencesHelper.currentVersion

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UiState.Error(exception)
    }

    init {
        getLatestVersion()
//        getSearchHistories()
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

    fun getFavorites() {
        launchCoroutineIO {
            val result = getFavoritesUseCase.invoke(Unit)
            _uiState.value = UiState.Success(result)
        }
    }

    fun updateFavoriteSummoner(join: SearchHistorySummonerJoin) {
        val summoner = Summoner(
            summonerName = join.summonerName,
            profileIconId = join.profileIconId,
            isFavorite = join.isFavorite,
            histories = listOf(
                Summoner.TierHistory(
                    tier = join.tier.javaClass.simpleName,
                    rank = join.tier.rank
                )
            )
        )
        launchCoroutineIO {
            updateFavoriteSummonerUseCase.invoke(summoner)
            getFavorites()
        }
    }
}
