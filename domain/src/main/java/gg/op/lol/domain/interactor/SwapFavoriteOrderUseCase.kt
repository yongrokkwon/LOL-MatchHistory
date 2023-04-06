package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SwapSummoner
import gg.op.lol.domain.repository.SummonerRepository
import javax.inject.Inject

typealias SwapFavoriteOrderBaseUseCase = BaseUseCase<SwapSummoner, Unit>

class SwapFavoriteOrderUseCase @Inject constructor(
    private val summonerRepository: SummonerRepository
) : SwapFavoriteOrderBaseUseCase {

    override suspend operator fun invoke(params: SwapSummoner) {
        summonerRepository.swapFavoriteSummoner(params)
    }
}
