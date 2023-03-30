package gg.op.lol.domain.repository

import gg.op.lol.domain.models.SearchHistory
import javax.inject.Singleton

@Singleton
interface SearchHistoryRepository {
    fun getSearchHistories(): List<SearchHistory>
    fun deleteSearchHistory(searchHistory: List<SearchHistory>): Boolean
    fun insertSearchHistory(searchHistory: SearchHistory)
}
