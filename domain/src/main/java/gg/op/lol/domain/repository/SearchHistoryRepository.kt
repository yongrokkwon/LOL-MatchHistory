package gg.op.lol.domain.repository

import gg.op.lol.domain.models.SearchHistory
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
interface SearchHistoryRepository {
    fun getSearchHistories(): Flow<List<SearchHistory>>
    fun insertSearchHistory(searchHistory: SearchHistory)
}
