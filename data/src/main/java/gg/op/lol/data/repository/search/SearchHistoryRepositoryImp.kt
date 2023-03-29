package gg.op.lol.data.repository.search

import gg.op.lol.data.local.dao.SearchHistoryDao
import gg.op.lol.data.mapper.SearchHistoryEntityMapper
import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.flow

@Singleton
class SearchHistoryRepositoryImp @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
    private val searchHistoryEntityMapper: SearchHistoryEntityMapper
) : SearchHistoryRepository {

    override fun getSearchHistories() = flow {
        val result = searchHistoryDao.getSearchHistory().map {
            searchHistoryEntityMapper.mapFromEntity(it)
        }
        emit(result)
    }

    override fun insertSearchHistory(searchHistory: SearchHistory) {
        val result = searchHistoryEntityMapper.mapToEntity(searchHistory)
        return searchHistoryDao.insertSearchHistory(result)
    }
}
