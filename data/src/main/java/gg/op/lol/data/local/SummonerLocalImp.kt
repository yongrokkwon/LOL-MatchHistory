package gg.op.lol.data.local

import gg.op.lol.data.local.dao.SummonerDao
import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.data.repository.SummonerLocal
import javax.inject.Inject

class SummonerLocalImp @Inject constructor(
    private val summonerDao: SummonerDao
) : SummonerLocal {

    override fun getSummonerByNickName(nickName: String): SummonerEntity? {
        return summonerDao.getSummonerByNickName(nickName)
    }

    override suspend fun insertSummoner(summoner: SummonerEntity) {
        summonerDao.insert(summoner)
    }

    override fun getSummoners(): List<SummonerEntity> {
        return summonerDao.getSummoners()
    }
}
