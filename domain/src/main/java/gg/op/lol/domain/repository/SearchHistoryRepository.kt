package gg.op.lol.domain.repository

import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.models.SearchHistorySummonerJoin
import javax.inject.Singleton

@Singleton
interface SearchHistoryRepository {
    fun getSearchHistories(): List<SearchHistorySummonerJoin>
    fun deleteSearchHistory(searchHistory: List<SearchHistorySummonerJoin>): Boolean
    fun insertSearchHistory(searchHistory: SearchHistory)
}
