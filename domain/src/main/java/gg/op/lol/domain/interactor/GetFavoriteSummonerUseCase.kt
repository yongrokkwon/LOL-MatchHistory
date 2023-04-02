package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.repository.SummonerRepository
import javax.inject.Inject

typealias GetFavoriteSummonerBaseUseCase = BaseUseCase<String, Summoner?>

class GetFavoriteSummonerUseCase @Inject constructor(
    private val summonerRepository: SummonerRepository
) : GetFavoriteSummonerBaseUseCase {

    override suspend operator fun invoke(params: String): Summoner? {
        return summonerRepository.getFavoriteSummoner(params)
    }
}
