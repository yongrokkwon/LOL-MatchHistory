package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetSearchHistoryBaseUseCase = BaseUseCase<Unit, Flow<List<SearchHistory>>>

class GetSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : GetSearchHistoryBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<List<SearchHistory>> {
        return searchHistoryRepository.getSearchHistories()
    }
}
