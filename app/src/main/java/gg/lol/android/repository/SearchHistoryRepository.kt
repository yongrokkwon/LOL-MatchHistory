package gg.lol.android.repository

import gg.lol.android.data.search.SearchHistory
import gg.lol.android.data.search.SearchHistoryDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchHistoryRepository @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
) {

    fun fetchSearchHistories() = searchHistoryDao.getSearchHistory()
    suspend fun insertSearchHistory(searchHistory: SearchHistory) = searchHistoryDao.insertSearchHistory(searchHistory)

}