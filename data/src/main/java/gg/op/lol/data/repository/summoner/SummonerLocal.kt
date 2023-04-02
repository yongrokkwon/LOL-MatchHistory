package gg.op.lol.data.repository.summoner

import gg.op.lol.data.local.models.SummonerEntity

interface SummonerLocal {
    fun getSummoners(): List<SummonerEntity>
    fun getSummoner(summonerName: String): SummonerEntity?
    fun getSummonerByNickName(nickName: String): SummonerEntity?

    fun updateSummoner(summoner: SummonerEntity)
}
