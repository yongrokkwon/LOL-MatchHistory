package gg.op.lol.data.repository

import gg.op.lol.data.models.SummonerEntity

interface SummonerLocal {
    fun getSummoners(): List<SummonerEntity>
    fun getSummonerByNickName(nickName: String): SummonerEntity?

    suspend fun insertSummoner(summoner: SummonerEntity)
}
