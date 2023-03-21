package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.SummonerHistory
import gg.op.lol.domain.repository.SummonerRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetSummonerInfoBaseUseCase = BaseUseCase<String, Flow<SummonerHistory>>

class GetSummonerInfoUseCase @Inject constructor(
    private val summonerRepository: SummonerRepository
) : GetSummonerInfoBaseUseCase {

    override suspend operator fun invoke(params: String): Flow<SummonerHistory> =
        summonerRepository.getRemoteSummoner(params)
}
