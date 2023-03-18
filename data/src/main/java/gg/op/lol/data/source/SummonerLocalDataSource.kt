package gg.op.lol.data.source

import gg.op.lol.data.models.SummonerEntity
import gg.op.lol.data.models.SummonerModel
import gg.op.lol.data.repository.SummonerDataSource
import gg.op.lol.data.repository.SummonerLocal
import javax.inject.Inject

class SummonerLocalDataSource @Inject constructor(
    private val summonerLocal: SummonerLocal
) : SummonerDataSource {

    override fun getSummoners(): List<SummonerEntity> {
        return summonerLocal.getSummoners()
    }

    override suspend fun getSummonerByNickName(nickName: String): SummonerModel {
        throw UnsupportedOperationException("Get SummonerByNickName is not supported for LocalDataSource.")
    }
}
