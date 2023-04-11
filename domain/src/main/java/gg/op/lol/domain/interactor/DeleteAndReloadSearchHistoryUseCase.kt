package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject

class DeleteAndReloadSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : BaseUseCase<List<SearchHistorySummonerJoin>, Pair<Boolean, List<SearchHistorySummonerJoin>>> {

    override suspend fun invoke(
        params: List<SearchHistorySummonerJoin>
    ): Pair<Boolean, List<SearchHistorySummonerJoin>> {
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
                        it.favoriteOrder,
                        isFavorite = false,
                        mySummoner = false
                    )
                }
            )
        }
    }

}
