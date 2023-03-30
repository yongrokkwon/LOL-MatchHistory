package gg.lol.android.ui.search

import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.exception.RoomDatabaseException
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.domain.interactor.DeleteAndReloadSearchHistoryUseCase
import gg.op.lol.domain.interactor.GetSearchHistoryUseCase
import gg.op.lol.domain.models.SearchHistory
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class SearchViewModel @Inject internal constructor(
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val deleteSearchHistoryUseCase: DeleteAndReloadSearchHistoryUseCase,
    private val preferencesHelper: PreferencesHelper
) : BaseViewModel() {

    private val _searchHistories = MutableStateFlow<UiState<List<SearchHistory>>>(UiState.Loading)
    val searchHistories: StateFlow<UiState<List<SearchHistory>>> = _searchHistories

    private val _latestVersion = MutableStateFlow(preferencesHelper.currentVersion)
    val latestVersion: StateFlow<String> = _latestVersion

    override val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace()
        }

    init {
        loadSearchHistories()
    }

    fun loadSearchHistories() {
        launchCoroutineIO {
            _searchHistories.value = UiState.Success(getSearchHistoryUseCase.invoke(Unit))
        }
    }

    fun deleteAndReloadHistory(params: List<SearchHistory>) {
        launchCoroutineIO {
            val result = deleteSearchHistoryUseCase.invoke(params)
            val isDeleted = result.first
            val histories = result.second
            _searchHistories.value = if (isDeleted) {
                UiState.Success(histories)
            } else {
                UiState.Error(RoomDatabaseException("Database Error"))
            }
        }
    }
}
