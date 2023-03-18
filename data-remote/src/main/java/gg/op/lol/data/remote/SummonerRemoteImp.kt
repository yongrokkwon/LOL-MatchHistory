package gg.op.lol.data.remote

import gg.op.lol.data.models.SummonerModel
import gg.op.lol.data.remote.api.UserService
import gg.op.lol.data.remote.mapper.SummonerRemoteMapper
import gg.op.lol.data.repository.SummonerRemote
import javax.inject.Inject

class SummonerRemoteImp @Inject constructor(
    private val userService: UserService,
    private val summonerRemoteMapper: SummonerRemoteMapper
) : SummonerRemote {

    override suspend fun getSummonerByNickName(nickName: String): SummonerModel {
        return summonerRemoteMapper.mapFromLocal(userService.getSummoner(nickName))
    }

}
