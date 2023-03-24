package gg.lol.android.ui.match

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.op.lol.domain.interactor.GetSummonerInfoUseCase
import gg.op.lol.domain.interactor.GetSummonerMatchHistoryUseCase
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.Summoner
import gg.lol.android.ui.UiState
import gg.lol.android.ui.BaseViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MatchHistoryViewModel @Inject internal constructor(
    private val summonerInfoUseCase: GetSummonerInfoUseCase,
    private val summonerMatchHistoryUseCase: GetSummonerMatchHistoryUseCase
) : BaseViewModel() {

    private val _summonerName = MutableStateFlow("")
    val summonerName: StateFlow<String> get() = _summonerName

    private val _appbarBackground = MutableStateFlow(Color.White)
    val appbarBackground: StateFlow<Color> get() = _appbarBackground

    private val _uiState = MutableStateFlow<UiState<Summoner>>(UiState.Loading)
    val uiState: StateFlow<UiState<Summoner>> get() = _uiState

    private val _matchHistories = MutableStateFlow<PagingData<MatchHistory>>(PagingData.empty())
    val matchHistories = _matchHistories

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UiState.Error(exception)
    }

    init {
        launchCoroutineIO {
            getRemoteSummoner()
        }
    }

    fun getMatchHistories(puuid: String) {
        launchCoroutineIO {
            summonerMatchHistoryUseCase.invoke(puuid).cachedIn(viewModelScope).collect {
                _matchHistories.value = it
            }
        }
    }

    private suspend fun getRemoteSummoner() {
        summonerInfoUseCase.invoke(summonerName.value).collect {
            _uiState.value = UiState.Success(it)
        }
    }

    fun setAppBarBackground(value: Color) {
        _appbarBackground.value = value
    }

    private val _screenCloseCheck = mutableStateOf(false)
    val screenCloseCheck get() = _screenCloseCheck.value

//    private val _summonerResponse = MutableLiveData<SummonerResponse>()
//    val summonerResponse: LiveData<SummonerResponse> get() = _summonerResponse

    fun setScreenCloseCheck(value: Boolean) {
        this._screenCloseCheck.value = value
    }

    fun setNickName(value: String) {
        _summonerName.value = value
    }

    fun getSummoner(name: String) {
        viewModelScope.launch {
            // TODO
        }
    }
}
