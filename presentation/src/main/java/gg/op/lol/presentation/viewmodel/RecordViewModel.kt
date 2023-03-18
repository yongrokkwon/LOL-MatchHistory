package gg.op.lol.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.op.lol.domain.interactor.GetSummonerInfoUseCase
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject internal constructor(
    private val summonerInfoUseCase: GetSummonerInfoUseCase
) : ViewModel() {

    private val _nickName = MutableLiveData<String>()
    val nickName get() = _nickName

    private val _appbarBackground = MutableLiveData<Color>()
    val appbarBackground get() = _appbarBackground

    suspend fun getRemoteSummoner() {
        viewModelScope.launch(Dispatchers.IO) {
            summonerInfoUseCase.invoke(nickName.value ?: "").collect {

            }
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