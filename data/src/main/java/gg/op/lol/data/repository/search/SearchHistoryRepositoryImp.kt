package gg.op.lol.data.repository.search

import gg.op.lol.data.local.dao.SearchHistoryDao
import gg.op.lol.data.local.models.SearchHistoryEntity
import gg.op.lol.data.mapper.SearchHistoryEntityMapper
import gg.op.lol.data.mapper.SearchSummonerMapper
import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchHistoryRepositoryImp @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
    private val searchHistoryEntityMapper: SearchHistoryEntityMapper,
    private val searchSummonerMapper: SearchSummonerMapper
) : SearchHistoryRepository {

    override fun getSearchHistories(): List<SearchHistorySummonerJoin> {
        val result = searchHistoryDao.getSearchHistory().map {
            searchSummonerMapper.mapFromEntity(it)
        }
        return result
    }

    override fun deleteSearchHistory(searchHistory: List<SearchHistorySummonerJoin>): Boolean {
        val entities = searchHistory.map { searchSummonerMapper.mapToEntity(it) }
        val deleteEntities = entities.map {
            SearchHistoryEntity(
                it.summonerName,
                it.profileIconId,
                it.tier,
                it.rank,
                it.lastSearchedAt
            )
        }
        return searchHistoryDao.delete(deleteEntities) == deleteEntities.size
    }

    override fun insertSearchHistory(searchHistory: SearchHistory) {
        val result = searchHistoryEntityMapper.mapToEntity(searchHistory)
        return searchHistoryDao.insertSearchHistory(result)
    }
}
