package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject

typealias BaseUseCaseReturnType = Pair<Boolean, List<SearchHistorySummonerJoin>>

interface DeleteAndReloadSearchHistoryBaseUseCase {
    operator fun invoke(params: List<SearchHistorySummonerJoin>): BaseUseCaseReturnType
}

class DeleteAndReloadSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : DeleteAndReloadSearchHistoryBaseUseCase {

    override operator fun invoke(params: List<SearchHistorySummonerJoin>): BaseUseCaseReturnType {
        val isDeleted = searchHistoryRepository.deleteSearchHistory(params)
        return if (isDeleted) {
            Pair(isDeleted, searchHistoryRepository.getSearchHistories())
        } else {
            Pair(
                isDeleted,
                params.map {
                    SearchHistorySummonerJoin(
                        it.summonerName,
                        it.summonerLevel,
                        it.profileIconId,
                        it.tier,
                        it.lastSearchedAt,
                        false,
                        false
                    )
                }
            )
        }
    }
}
