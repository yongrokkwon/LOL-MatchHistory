package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.repository.SearchHistoryRepository
import javax.inject.Inject

interface GetFavoriteBaseUseCase {
    operator fun invoke(params: Unit): List<SearchHistorySummonerJoin>
}

class GetFavoritesUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : GetFavoriteBaseUseCase {

    override operator fun invoke(params: Unit): List<SearchHistorySummonerJoin> {
        return searchHistoryRepository.getFavorites()
    }
}
