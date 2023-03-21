package gg.op.lol.data.remote

import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.data.models.SummonerInfoEntity
import gg.op.lol.data.remote.api.SummonerService
import gg.op.lol.data.remote.mapper.SummonerHistoryMapper
import gg.op.lol.data.remote.mapper.SummonerInfoMapper
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.repository.SummonerRemote
import javax.inject.Inject

class SummonerRemoteImp @Inject constructor(
    private val summonerService: SummonerService,
    private val summonerHistoryMapper: SummonerHistoryMapper,
    private val summonerInfoMapper: SummonerInfoMapper
) : SummonerRemote {

    override suspend fun getSummonerHistory(id: String): SummonerHistoryEntity {
        val summonerHistory = summonerService.getSummonerHistory(id)
        val summonerUpdateHistory = updateUnRank(summonerHistory)
        return summonerHistoryMapper.mapFromLocal(summonerUpdateHistory)
    }

    override suspend fun getSummonerInfo(nickName: String): SummonerInfoEntity {
        return summonerInfoMapper.mapFromLocal(summonerService.getSummonerInfo(nickName))
    }

    private fun updateUnRank(list: SummonerHistoryResponse): SummonerHistoryResponse {
        for (i in list.size until 2) {
            list.add(SummonerHistoryResponse.Item())
        }
        return list
    }
}
