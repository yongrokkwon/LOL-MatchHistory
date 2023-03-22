package gg.op.lol.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.data.models.SummonerInfoEntity
import gg.op.lol.data.remote.api.MatchService
import gg.op.lol.data.remote.api.SummonerService
import gg.op.lol.data.remote.mapper.MatchHistoryMapper
import gg.op.lol.data.remote.mapper.SummonerHistoryMapper
import gg.op.lol.data.remote.mapper.SummonerInfoMapper
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.repository.SummonerRemote
import gg.op.lol.domain.models.MatchHistory
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SummonerRemoteImp @Inject constructor(
    private val summonerService: SummonerService,
    private val matchService: MatchService,
    private val summonerHistoryMapper: SummonerHistoryMapper,
    private val summonerInfoMapper: SummonerInfoMapper,
    private val matchHistoryMapper: MatchHistoryMapper
) : SummonerRemote {

    override suspend fun getSummonerHistory(id: String): SummonerHistoryEntity {
        val summonerHistory = summonerService.getSummonerHistory(id)
        val summonerUpdateHistory = updateUnRank(summonerHistory)
        return summonerHistoryMapper.mapFromLocal(summonerUpdateHistory)
    }

    override suspend fun getSummonerInfo(nickName: String): SummonerInfoEntity {
        return summonerInfoMapper.mapFromLocal(summonerService.getSummonerInfo(nickName))
    }

    override fun getMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_COUNT),
            pagingSourceFactory = {
                MatchHistoryPagingSource(
                    matchService,
                    matchHistoryMapper,
                    puuid
                )
            }
        ).flow
    }

    private fun updateUnRank(list: SummonerHistoryResponse): SummonerHistoryResponse {
        for (i in list.size until 2) {
            list.add(SummonerHistoryResponse.Item())
        }
        return list
    }
}
