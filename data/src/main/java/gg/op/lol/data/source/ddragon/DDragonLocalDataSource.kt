package gg.op.lol.data.source.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.data.local.models.SpellEntity
import gg.op.lol.data.repository.ddragon.DDragonDataSource
import gg.op.lol.data.repository.ddragon.DDragonLocal
import gg.op.lol.domain.models.ChampionRuneItemSpell
import javax.inject.Inject

class DDragonLocalDataSource @Inject constructor(
    private val ddragonLocal: DDragonLocal
) : DDragonDataSource {

    fun delete() {
        ddragonLocal.delete()
    }

    fun insert(championRuneItemSpell: ChampionRuneItemSpell) {
        ddragonLocal.insert(championRuneItemSpell)
    }

    fun getLocalRunes(): List<RuneEntity> {
        return ddragonLocal.getRunes()
    }

    fun getLocalItems(): List<ItemEntity> {
        return ddragonLocal.getItems()
    }

    fun getChampion(id: Int): ChampionEntity? {
        return ddragonLocal.getChampion(id)
    }

    fun getLocalChampions(): List<ChampionEntity> {
        return ddragonLocal.getChampions()
    }

    fun getLocalSpells(): List<SpellEntity> {
        return ddragonLocal.getSpells()
    }
}
