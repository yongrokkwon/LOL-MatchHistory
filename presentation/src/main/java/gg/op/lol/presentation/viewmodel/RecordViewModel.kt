package gg.op.lol.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.op.lol.domain.interactor.GetSummonerInfoUseCase
import gg.op.lol.domain.models.Summoner
import gg.op.lol.presentation.UiState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject internal constructor(
    private val summonerInfoUseCase: GetSummonerInfoUseCase
) : BaseViewModel() {

    // TODO Refactor
    private val _nickName = MutableLiveData<String>()
    val nickName get() = _nickName

    private val _appbarBackground = MutableLiveData<Color>()
    val appbarBackground get() = _appbarBackground

    private val _uiState = MutableStateFlow<UiState<Summoner>>(UiState.Loading)
    val uiState get() = _uiState

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UiState.Error(exception)
    }

    init {
        launchCoroutineIO {
            getRemoteSummoner()
        }
    }

    suspend fun getRemoteSummoner() {
        summonerInfoUseCase.invoke(nickName.value ?: "").collect {
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
        _nickName.value = value
    }

    fun getSummoner(name: String) {
        viewModelScope.launch {
//            when (val result = summonerInfoUseCase.getSummoner(name)) {
//                is Result.Success -> _summonerResponse.value = result.data
//                is Result.Error -> Log.e("RecordViewModel", "getSummonerError", result.exception)
//            }
        }
    }
}
