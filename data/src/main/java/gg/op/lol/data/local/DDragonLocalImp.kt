package gg.op.lol.data.local

import gg.op.lol.data.local.dao.ChampionDao
import gg.op.lol.data.local.dao.ItemDao
import gg.op.lol.data.local.dao.RuneDao
import gg.op.lol.data.local.dao.SpellDao
import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.data.local.models.SpellEntity
import gg.op.lol.data.repository.ddragon.DDragonLocal
import javax.inject.Inject

class DDragonLocalImp @Inject constructor(
    private val championDao: ChampionDao,
    private val spellDao: SpellDao,
    private val itemDao: ItemDao,
    private val runeDao: RuneDao
) : DDragonLocal {

    override fun getRunes(): List<RuneEntity> = runeDao.getAll()
    override fun getItems(): List<ItemEntity> = itemDao.getAll()
    override fun getChampions(): List<ChampionEntity> = championDao.getAll()
    override fun getChampion(key: String): ChampionEntity? = championDao.findByKey(key)
    override fun getSpells(): List<SpellEntity> = spellDao.getAll()

    override fun insertRune(runeEntity: RuneEntity) {
        runeDao.insert(runeEntity)
    }

    override fun insertItem(itemEntity: ItemEntity) {
        itemDao.insert(itemEntity)
    }

    override fun insertSpell(spellEntity: SpellEntity) {
        spellDao.insert(spellEntity)
    }

    override fun insertChampion(championEntity: ChampionEntity) {
        championDao.insert(championEntity)
    }
}
