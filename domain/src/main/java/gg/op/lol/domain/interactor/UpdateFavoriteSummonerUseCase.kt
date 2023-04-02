package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.repository.SummonerRepository
import javax.inject.Inject

typealias InsertFavoriteSummonerBaseUseCase = BaseUseCase<Summoner, Unit>

class UpdateFavoriteSummonerUseCase @Inject constructor(
    private val summonerRepository: SummonerRepository
) : InsertFavoriteSummonerBaseUseCase {

    override suspend operator fun invoke(params: Summoner) {
        summonerRepository.updateFavoriteSummoner(params)
    }
}
