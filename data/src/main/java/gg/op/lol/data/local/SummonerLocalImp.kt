package gg.op.lol.data.local

import gg.op.lol.data.local.dao.SummonerDao
import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.data.repository.summoner.SummonerLocal
import gg.op.lol.domain.models.SwapSummoner
import javax.inject.Inject

class SummonerLocalImp @Inject constructor(
    private val summonerDao: SummonerDao
) : SummonerLocal {

    override fun getSummonerByNickName(nickName: String): SummonerEntity {
        return summonerDao.getBySummonerName(nickName)
    }

    override fun swapFavoriteSummoner(swapSummoner: SwapSummoner) {
        summonerDao.swapFavoriteOrder(
            swapSummoner.firstSummonerJoin.summonerName,
            swapSummoner.firstIndex,
            swapSummoner.secondSummonerJoin.summonerName,
            swapSummoner.secondIndex
        )
    }

    override fun updateSummoner(summoner: SummonerEntity) {
        val favoriteOrder = if (summoner.isFavorite) {
            summonerDao.getMaxFavoriteOrder() + 1
        } else {
            0
        }
        summonerDao.insertOrUpdate(summoner.copy(favoriteOrder = favoriteOrder))
    }

    override fun getSummoner(summonerName: String): SummonerEntity {
        return summonerDao.getBySummonerName(summonerName)
    }

    override fun getSummoners(): List<SummonerEntity> {
        return summonerDao.getFavorites()
    }
}
