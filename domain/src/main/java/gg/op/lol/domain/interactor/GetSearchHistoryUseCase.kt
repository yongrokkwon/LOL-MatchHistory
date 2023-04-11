package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject

typealias GetSearchHistoryBaseUseCase = BaseUseCase<Unit, List<SearchHistorySummonerJoin>>

class GetSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : GetSearchHistoryBaseUseCase {

    override suspend fun invoke(params: Unit): List<SearchHistorySummonerJoin> {
        return searchHistoryRepository.getSearchHistories()
    }

}
