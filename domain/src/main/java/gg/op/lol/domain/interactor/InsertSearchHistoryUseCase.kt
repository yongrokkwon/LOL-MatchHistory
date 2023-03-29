package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject

typealias InsertSearchHistoryBaseUseCase = BaseUseCase<SearchHistory, Unit>

class InsertSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : InsertSearchHistoryBaseUseCase {

    override suspend operator fun invoke(params: SearchHistory) {
        return searchHistoryRepository.insertSearchHistory(params)
    }
}
