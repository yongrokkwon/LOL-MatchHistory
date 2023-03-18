package gg.op.lol.data.repository

import gg.op.lol.data.models.SummonerEntity
import gg.op.lol.data.models.SummonerModel

interface SummonerDataSource {
    // Local
    fun getSummoners(): List<SummonerEntity>

    // Remote
    suspend fun getSummonerByNickName(nickName: String): SummonerModel
}
