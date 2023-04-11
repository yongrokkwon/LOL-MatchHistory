package gg.op.lol.domain.interactor

import androidx.paging.PagingData
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.repository.SummonerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetPagingMatchHistoriesBaseUseCase = BaseUseCase<String, Flow<PagingData<MatchHistory>>>

class GetPagingMatchHistoriesUseCase @Inject constructor(
    private val summonerRepository: SummonerRepository
) : GetPagingMatchHistoriesBaseUseCase {

    override suspend fun invoke(params: String): Flow<PagingData<MatchHistory>> {
        return summonerRepository.getRemotePagingMatchHistory(params)
    }

}
