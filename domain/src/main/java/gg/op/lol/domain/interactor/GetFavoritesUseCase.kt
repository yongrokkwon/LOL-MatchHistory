package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject

typealias GetFavoriteBaseUseCase = BaseUseCase<Unit, List<SearchHistorySummonerJoin>>

class GetFavoritesUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : GetFavoriteBaseUseCase {

    override suspend operator fun invoke(params: Unit): List<SearchHistorySummonerJoin> {
        return searchHistoryRepository.getFavorites()
    }
}
