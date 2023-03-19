package gg.op.lol.data.remote

import gg.op.lol.data.models.SummonerHistoryModel
import gg.op.lol.data.remote.api.UserService
import gg.op.lol.data.remote.mapper.SummonerRemoteMapper
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.repository.SummonerRemote
import javax.inject.Inject

class SummonerRemoteImp @Inject constructor(
    private val userService: UserService,
    private val summonerRemoteMapper: SummonerRemoteMapper
) : SummonerRemote {

    override suspend fun getSummoner(nickName: String): SummonerHistoryModel {
        return summonerRemoteMapper.mapFromLocal(getSummonerInfo(nickName))
    }

    private suspend fun getSummonerInfo(nickName: String): SummonerHistoryResponse {
        return getSummonerHistory(userService.getSummonerInfo(nickName).id)
    }

    private suspend fun getSummonerHistory(id: String): SummonerHistoryResponse {
        return userService.getSummonerHistory(id)
    }
}
