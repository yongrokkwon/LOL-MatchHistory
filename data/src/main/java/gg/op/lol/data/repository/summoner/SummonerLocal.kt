package gg.op.lol.data.repository.summoner

import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.domain.models.SwapSummoner

interface SummonerLocal {
    fun getSummoners(): List<SummonerEntity>
    fun getSummoner(summonerName: String): SummonerEntity?
    fun getSummonerByNickName(nickName: String): SummonerEntity?
    fun swapFavoriteSummoner(swapSummoner: SwapSummoner)

    fun updateSummoner(summoner: SummonerEntity)
}
