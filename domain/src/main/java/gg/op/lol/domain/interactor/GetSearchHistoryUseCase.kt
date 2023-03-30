package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject

interface GetSearchHistoryBaseUseCase {
    operator fun invoke(params: Unit): List<SearchHistory>
}

class GetSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : GetSearchHistoryBaseUseCase {

    override operator fun invoke(params: Unit): List<SearchHistory> {
        return searchHistoryRepository.getSearchHistories()
    }
}
