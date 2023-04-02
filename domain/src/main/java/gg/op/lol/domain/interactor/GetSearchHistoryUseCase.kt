package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject

interface GetSearchHistoryBaseUseCase {
    operator fun invoke(params: Unit): List<SearchHistorySummonerJoin>
}

class GetSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : GetSearchHistoryBaseUseCase {

    override operator fun invoke(params: Unit): List<SearchHistorySummonerJoin> {
        return searchHistoryRepository.getSearchHistories()
    }
}
