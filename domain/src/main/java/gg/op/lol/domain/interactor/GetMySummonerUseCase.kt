package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.MySummoner
import gg.op.lol.domain.repository.SummonerRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetMySummonerBaseUseCase = BaseUseCase<String, Flow<MySummoner>>

class GetMySummonerUseCase @Inject constructor(
    private val summonerRepository: SummonerRepository
) : GetMySummonerBaseUseCase {

    override suspend fun invoke(params: String): Flow<MySummoner> =
        summonerRepository.getMySummoner(params)
}
