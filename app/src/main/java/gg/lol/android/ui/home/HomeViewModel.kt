package gg.lol.android.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.BuildConfig
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.domain.interactor.GetFavoritesUseCase
import gg.op.lol.domain.interactor.GetMySummonerUseCase
import gg.op.lol.domain.interactor.GetSummonerInfoUseCase
import gg.op.lol.domain.interactor.SwapFavoriteOrderUseCase
import gg.op.lol.domain.interactor.UpdateFavoriteSummonerUseCase
import gg.op.lol.domain.models.MySummoner
import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.models.SwapSummoner
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class HomeViewModel @Inject internal constructor(
    private val savedStateHandle: SavedStateHandle,
    private val preferencesHelper: PreferencesHelper,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val updateFavoriteSummonerUseCase: UpdateFavoriteSummonerUseCase,
    private val swapFavoriteOrderUseCase: SwapFavoriteOrderUseCase,
    private val summonerInfoUseCase: GetSummonerInfoUseCase,
    private val getMySummonerUseCase: GetMySummonerUseCase
) : BaseViewModel() {

    private val _mySummoner = MutableStateFlow<MySummoner?>(null)
    val mySummoner: StateFlow<MySummoner?> = _mySummoner

    private val _favorites = mutableStateOf(emptyList<SearchHistorySummonerJoin>())
    val favorites: State<List<SearchHistorySummonerJoin>> get() = _favorites

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val uiState: StateFlow<UiState<Unit>> get() = _uiState

    val lolApiVersion = preferencesHelper.lolApiVersion

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (BuildConfig.DEBUG) throwable.printStackTrace()
        _uiState.value = UiState.Error(throwable)
    }

    fun setFavorites(value: List<SearchHistorySummonerJoin>) {
        _favorites.value = value
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

    fun getMatchHistories(summonerName: String) {
        launchCoroutineIO {
            getMySummonerUseCase.invoke(summonerName).collect {
                _mySummoner.value = it
            }
        }
    }
}
