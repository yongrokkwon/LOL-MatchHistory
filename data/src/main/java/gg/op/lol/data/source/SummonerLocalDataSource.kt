package gg.op.lol.data.source

import gg.op.lol.data.models.SummonerEntity
import gg.op.lol.data.models.SummonerHistoryModel
import gg.op.lol.data.models.SummonerInfoModel
import gg.op.lol.data.repository.SummonerDataSource
import gg.op.lol.data.repository.SummonerLocal
import javax.inject.Inject

class SummonerLocalDataSource @Inject constructor(
    private val summonerLocal: SummonerLocal
) : SummonerDataSource {

    override fun getSummoners(): List<SummonerEntity> {
        return summonerLocal.getSummoners()
    }

    override suspend fun getSummonerHistory(id: String): SummonerHistoryModel {
        throw UnsupportedOperationException(
            "Get SummonerByNickName is not supported for LocalDataSource."
        )
    }

    override suspend fun getSummonerInfo(nickName: String): SummonerInfoModel {
        throw UnsupportedOperationException(
            "Get getSummonerInfo is not supported for LocalDataSource."
        )
    }
}
