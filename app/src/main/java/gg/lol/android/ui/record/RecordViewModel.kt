package gg.lol.android.ui.record

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.data.search.SearchHistory
import gg.lol.android.repository.SearchHistoryRepository
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject internal constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {

    val searchHistories = searchHistoryRepository.fetchSearchHistories().asLiveData()

    private val _appbarTitle = MutableLiveData<String>()
    val appBarTitle get() = _appbarTitle

    private val _appbarBackground = MutableLiveData<Color>()
    val appbarBackground get() = _appbarBackground

    fun setAppBarBackground(value: Color) {
        _appbarBackground.value = value
    }

    private val _screenCloseCheck = mutableStateOf(false)
    val screenCloseCheck get() = _screenCloseCheck.value

    fun setScreenCloseCheck(value: Boolean) {
        this._screenCloseCheck.value = value
    }

    fun setAppBarTitle(value: String) {
        _appbarTitle.value = value
    }

    fun insert(searchHistory: SearchHistory) {
        viewModelScope.launch {
            searchHistoryRepository.insertSearchHistory(searchHistory)
            Log.d("###", "insert success")
        }
    }

}