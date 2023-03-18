package gg.op.lol.data.local

import gg.op.lol.data.local.dao.SummonerDao
import gg.op.lol.data.local.mapper.SummonerLocalMapper
import gg.op.lol.data.models.SummonerEntity
import gg.op.lol.data.repository.SummonerLocal
import javax.inject.Inject

class SummonerLocalImp @Inject constructor(
    private val summonerDao: SummonerDao,
    private val summonerLocalMapper: SummonerLocalMapper
) : SummonerLocal {

    override fun getSummonerByNickName(nickName: String): SummonerEntity? {
        return summonerDao.getSummonerByNickName(nickName)
            ?.let { summonerLocalMapper.mapFromLocal(it) }
    }

    override suspend fun insertSummoner(summoner: SummonerEntity) {
        summonerDao.insert(summonerLocalMapper.mapToLocal(summoner))
    }

    override fun getSummoners(): List<SummonerEntity> {
        return summonerDao.getSummoners().map {
            summonerLocalMapper.mapFromLocal(it)
        }
    }
}
