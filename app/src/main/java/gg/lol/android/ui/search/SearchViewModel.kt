package gg.lol.android.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.data.search.SearchHistory
import gg.op.lol.data.SearchHistoryRepository
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject internal constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {

//    val searchHistories = searchHistoryRepository.fetchSearchHistories().asLiveData()

    fun insert(searchHistory: SearchHistory) {
        viewModelScope.launch {
//            searchHistoryRepository.insertSearchHistory(searchHistory)
            Log.d("###", "insert success")
        }
    }
}
