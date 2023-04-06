package gg.lol.android.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import gg.op.lol.domain.interactor.SwapFavoriteOrderUseCase
import gg.op.lol.domain.interactor.UpdateFavoriteSummonerUseCase
import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.models.SwapSummoner
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
    private val preferencesHelper: PreferencesHelper,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val updateFavoriteSummonerUseCase: UpdateFavoriteSummonerUseCase,
    private val swapFavoriteOrderUseCase: SwapFavoriteOrderUseCase
) : BaseViewModel() {

    private val _favorites = mutableStateOf(emptyList<SearchHistorySummonerJoin>())
    val favorites: State<List<SearchHistorySummonerJoin>> get() = _favorites

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val uiState: StateFlow<UiState<Unit>> get() = _uiState

    val latestVersion get() = preferencesHelper.currentVersion

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _uiState.value = UiState.Error(exception)
    }

    init {
        getLatestVersion()
//        getSearchHistories()
    }

    fun setFavorites(value: List<SearchHistorySummonerJoin>) {
        _favorites.value = value
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
            _favorites.value = result
        }
    }

    fun swapFavoriteOrder(summonerPair: SwapSummoner) {
        launchCoroutineIO {
            swapFavoriteOrderUseCase.invoke(summonerPair)
        }
    }

    fun updateFavoriteSummoner(join: SearchHistorySummonerJoin) {
        val summoner = Summoner(
            summonerName = join.summonerName,
            summonerLevel = join.summonerLevel,
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
