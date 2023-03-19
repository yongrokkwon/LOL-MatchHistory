package gg.op.lol.data.repository

import gg.op.lol.data.models.SummonerEntity
import gg.op.lol.data.models.SummonerHistoryModel

interface SummonerDataSource {
    // Local
    fun getSummoners(): List<SummonerEntity>

    // Remote
    suspend fun getSummoner(nickName: String): SummonerHistoryModel
}
