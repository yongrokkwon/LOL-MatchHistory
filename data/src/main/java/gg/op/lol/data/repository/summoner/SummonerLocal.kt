package gg.op.lol.data.repository.summoner

import gg.op.lol.data.local.models.SummonerEntity

interface SummonerLocal {
    fun getSummoners(): List<SummonerEntity>
    fun getSummonerByNickName(nickName: String): SummonerEntity?

    suspend fun insertSummoner(summoner: SummonerEntity)
}