package gg.op.lol.domain.interactor

import androidx.paging.PagingData
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.repository.SummonerRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetPagingMatchHistoriesBaseUseCase<in Parameter, out Result> {
    operator fun invoke(params: Parameter): Result
}

class GetPagingMatchHistoriesUseCase @Inject constructor(
    private val summonerRepository: SummonerRepository
) : GetPagingMatchHistoriesBaseUseCase<String, Flow<PagingData<MatchHistory>>> {

    override operator fun invoke(params: String): Flow<PagingData<MatchHistory>> =
        summonerRepository.getRemotePagingMatchHistory(params)
}
