package gg.lol.android.ui.search

import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.exception.RoomDatabaseException
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.domain.interactor.DeleteAndReloadSearchHistoryUseCase
import gg.op.lol.domain.interactor.GetFavoriteSummonerUseCase
import gg.op.lol.domain.interactor.GetSearchHistoryUseCase
import gg.op.lol.domain.interactor.UpdateFavoriteSummonerUseCase
import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

typealias SearchViewModelUiState = UiState<List<SearchHistorySummonerJoin>>

@HiltViewModel
class SearchViewModel @Inject internal constructor(
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val deleteSearchHistoryUseCase: DeleteAndReloadSearchHistoryUseCase,
    private val preferencesHelper: PreferencesHelper,
    private val updateFavoriteSummonerUseCase: UpdateFavoriteSummonerUseCase,
    private val getFavoriteSummonerUseCase: GetFavoriteSummonerUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<SearchViewModelUiState>(UiState.Loading)
    val uiState: StateFlow<SearchViewModelUiState> = _uiState

    private val _latestVersion = MutableStateFlow(preferencesHelper.currentVersion)
    val latestVersion: StateFlow<String> = _latestVersion

    override val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace()
        }

    init {
        loadSearchHistories()
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
            getFavoriteSummoner(summoner.summonerName)
        }
    }

    fun getFavoriteSummoner(summonerName: String) {
        launchCoroutineIO {
            val result = getFavoriteSummonerUseCase.invoke(summonerName)
            result ?: return@launchCoroutineIO
            val state = uiState.value
            if (state is UiState.Success<List<SearchHistorySummonerJoin>>) {
                _uiState.value = state.copy(
                    data = state.data.map {
                        if (it.summonerName == summonerName) {
                            it.copy(isFavorite = result.isFavorite)
                        } else {
                            it
                        }
                    }
                )
            }
        }
    }

    fun loadSearchHistories() {
        launchCoroutineIO {
            val result = getSearchHistoryUseCase.invoke(Unit)
            _uiState.value = UiState.Success(result)
        }
    }

    fun deleteAndReloadHistory(params: List<SearchHistorySummonerJoin>) {
        launchCoroutineIO {
            val result = deleteSearchHistoryUseCase.invoke(params)
            val isDeleted = result.first
            val histories = result.second
            _uiState.value = if (isDeleted) {
                UiState.Success(histories)
            } else {
                UiState.Error(RoomDatabaseException("Database Error"))
            }
        }
    }
}
