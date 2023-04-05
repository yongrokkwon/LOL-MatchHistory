package gg.op.lol.data.local

import gg.op.lol.data.local.dao.SummonerDao
import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.data.repository.summoner.SummonerLocal
import javax.inject.Inject

class SummonerLocalImp @Inject constructor(
    private val summonerDao: SummonerDao
) : SummonerLocal {

    override fun getSummonerByNickName(nickName: String): SummonerEntity {
        return summonerDao.getBySummonerName(nickName)
    }

    override fun updateSummoner(summoner: SummonerEntity) {
        summonerDao.insertOrUpdate(summoner)
        summonerDao.updateIntValueToNextById(summoner.summonerName)
    }

    override fun getSummoner(summonerName: String): SummonerEntity {
        return summonerDao.getBySummonerName(summonerName)
    }

    override fun getSummoners(): List<SummonerEntity> {
        return summonerDao.getFavorites()
    }
}
