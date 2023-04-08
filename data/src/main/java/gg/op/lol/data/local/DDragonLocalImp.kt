package gg.op.lol.data.local

import gg.op.lol.data.local.dao.ChampionDao
import gg.op.lol.data.local.dao.GameDataDao
import gg.op.lol.data.local.dao.ItemDao
import gg.op.lol.data.local.dao.RuneDao
import gg.op.lol.data.local.dao.SpellDao
import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.data.local.models.SpellEntity
import gg.op.lol.data.repository.ddragon.DDragonLocal
import gg.op.lol.domain.models.ChampionRuneItemSpell
import javax.inject.Inject

class DDragonLocalImp @Inject constructor(
    private val gameDataDao: GameDataDao,
    private val championDao: ChampionDao,
    private val spellDao: SpellDao,
    private val itemDao: ItemDao,
    private val runeDao: RuneDao
) : DDragonLocal {

    override fun getRunes(): List<RuneEntity> = runeDao.getAll()
    override fun getItems(): List<ItemEntity> = itemDao.getAll()
    override fun getChampions(): List<ChampionEntity> = championDao.getAll()
    override fun getChampion(id: Int): ChampionEntity? = championDao.findByKey(id)
    override fun getSpells(): List<SpellEntity> = spellDao.getAll()

    override fun insert(championEntities: ChampionRuneItemSpell) {
        gameDataDao.insertData(
            championEntities.champion.map {
                ChampionEntity(
                    it.id,
                    it.name,
                    it.imagePath
                )
            },
            championEntities.rune.map {
                RuneEntity(
                    it.icon,
                    it.id,
                    it.bodies.map { RuneEntity.Body(it.icon, it.id) }
                )
            },
            championEntities.item.map {
                ItemEntity(
                    it.id,
                    it.full,
                    it.group
                )
            },
            championEntities.spell.map {
                SpellEntity(it.id, it.imagePath)
            }
        )
    }

    override fun delete() {
        gameDataDao.deleteData()
    }
}
