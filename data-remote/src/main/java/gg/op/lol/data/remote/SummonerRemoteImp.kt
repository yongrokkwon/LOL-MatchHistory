package gg.op.lol.data.remote

import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.data.models.SummonerInfoEntity
import gg.op.lol.data.remote.api.UserService
import gg.op.lol.data.remote.mapper.SummonerHistoryMapper
import gg.op.lol.data.remote.mapper.SummonerInfoMapper
import gg.op.lol.data.repository.SummonerRemote
import javax.inject.Inject

class SummonerRemoteImp @Inject constructor(
    private val userService: UserService,
    private val summonerHistoryMapper: SummonerHistoryMapper,
    private val summonerInfoMapper: SummonerInfoMapper
) : SummonerRemote {

    override suspend fun getSummonerHistory(id: String): SummonerHistoryEntity {
        return summonerHistoryMapper.mapFromLocal(userService.getSummonerHistory(id))
    }

    override suspend fun getSummonerInfo(nickName: String): SummonerInfoEntity {
        return summonerInfoMapper.mapFromLocal(userService.getSummonerInfo(nickName))
    }
}
