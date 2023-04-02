package gg.op.lol.data.local

import gg.op.lol.data.local.dao.SummonerDao
import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.data.repository.summoner.SummonerLocal
import javax.inject.Inject

class SummonerLocalImp @Inject constructor(
    private val summonerDao: SummonerDao
) : SummonerLocal {

    override fun getSummonerByNickName(nickName: String): SummonerEntity? {
        return summonerDao.getSummonerByNickName(nickName)
    }

    override fun updateSummoner(summoner: SummonerEntity) {
        summonerDao.update(summoner)
    }

    override fun getSummoner(summonerName: String): SummonerEntity? {
        return summonerDao.getSummonerByNickName(summonerName)
    }

    override fun getSummoners(): List<SummonerEntity> {
        return summonerDao.getSummoners()
    }
}
