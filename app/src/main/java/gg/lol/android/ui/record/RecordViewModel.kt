package gg.lol.android.ui.record

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.data.search.SearchHistory
import gg.lol.android.network.Result
import gg.lol.android.network.response.SummonerResponse
import gg.lol.android.repository.SearchHistoryRepository
import gg.lol.android.repository.SummonerRepository
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject internal constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val summonerRepository: SummonerRepository
) : ViewModel() {

    val searchHistories = searchHistoryRepository.fetchSearchHistories().asLiveData()

    private val _nickName = MutableLiveData<String>()
    val nickName get() = _nickName

    private val _appbarBackground = MutableLiveData<Color>()
    val appbarBackground get() = _appbarBackground

    fun getSummonerDB() = summonerRepository.getSummonerByNickName(nickName.value ?: "")

    fun setAppBarBackground(value: Color) {
        _appbarBackground.value = value
    }

    private val _screenCloseCheck = mutableStateOf(false)
    val screenCloseCheck get() = _screenCloseCheck.value

    private val _summonerResponse = MutableLiveData<SummonerResponse>()
    val summonerResponse: LiveData<SummonerResponse> get() = _summonerResponse

    fun setScreenCloseCheck(value: Boolean) {
        this._screenCloseCheck.value = value
    }

    fun setNickName(value: String) {
        _nickName.value = value
    }

    fun insert(searchHistory: SearchHistory) {
        viewModelScope.launch {
            searchHistoryRepository.insertSearchHistory(searchHistory)
            Log.d("###", "insert success")
        }
    }


    fun getSummoner(name: String) {
        viewModelScope.launch {
            when (val result = summonerRepository.getSummoner(name)) {
                is Result.Success -> _summonerResponse.value = result.data
                is Result.Error -> Log.e("RecordViewModel", "getSummonerError", result.exception)
            }
        }
    }

}