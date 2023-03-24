package gg.op.lol.data.local

import gg.op.lol.data.local.dao.ChampionDao
import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.repository.ddragon.DDragonLocal
import javax.inject.Inject

class DDragonLocalImp @Inject constructor(
    private val championDao: ChampionDao
) : DDragonLocal {
    override fun getChampions(): List<ChampionEntity> {
        return championDao.getAll()
    }

    override fun getChampion(key: String): ChampionEntity? {
        return championDao.findByKey(key)
    }

    override fun insertChampion(championEntity: ChampionEntity) {
        championDao.insert(championEntity)
    }
}
