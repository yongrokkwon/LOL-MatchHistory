package gg.op.lol.data.source

import gg.op.lol.data.models.SummonerEntity
import gg.op.lol.data.models.SummonerModel
import gg.op.lol.data.repository.SummonerDataSource
import gg.op.lol.data.repository.SummonerRemote
import javax.inject.Inject

class SummonerRemoteDataSource @Inject constructor(
    private val summonerRemote: SummonerRemote
) : SummonerDataSource {

    override fun getSummoners(): List<SummonerEntity> {
        throw UnsupportedOperationException("Get Summoners is not supported for RemoteDataSource.")
    }

    override suspend fun getSummonerByNickName(nickName: String): SummonerModel {
        return summonerRemote.getSummonerByNickName(nickName)
    }
}
