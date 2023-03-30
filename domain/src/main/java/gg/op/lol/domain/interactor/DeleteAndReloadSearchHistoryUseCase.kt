package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject

interface DeleteAndReloadSearchHistoryBaseUseCase {
    operator fun invoke(params: List<SearchHistory>): Pair<Boolean, List<SearchHistory>>
}

class DeleteAndReloadSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : DeleteAndReloadSearchHistoryBaseUseCase {

    override operator fun invoke(params: List<SearchHistory>): Pair<Boolean, List<SearchHistory>> {
        val isDeleted = searchHistoryRepository.deleteSearchHistory(params)
        return if (isDeleted) {
            Pair(isDeleted, searchHistoryRepository.getSearchHistories())
        } else {
            Pair(isDeleted, params.toList())
        }
    }
}
