package gg.op.lol.data.source.summoner

import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.data.repository.summoner.SummonerDataSource
import gg.op.lol.data.repository.summoner.SummonerLocal
import gg.op.lol.domain.models.SwapSummoner
import javax.inject.Inject

class SummonerLocalDataSource @Inject constructor(
    private val summonerLocal: SummonerLocal
) : SummonerDataSource {

    fun getSummoners(): List<SummonerEntity> {
        return summonerLocal.getSummoners()
    }

    fun getSummoner(summonerName: String): SummonerEntity? {
        return summonerLocal.getSummoner(summonerName)
    }

    fun updateFavoriteSummoner(summoner: SummonerEntity) {
        summonerLocal.updateSummoner(summoner)
    }

    fun swapFavoriteSummoner(swapSummoner: SwapSummoner) {
        summonerLocal.swapFavoriteSummoner(swapSummoner)
    }
}
