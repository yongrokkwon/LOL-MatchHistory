package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.data.local.models.SpellEntity
import gg.op.lol.domain.models.ChampionRuneItemSpell

interface DDragonLocal {
    fun getChampion(id: Int): ChampionEntity?
    fun getChampions(): List<ChampionEntity>
    fun getRunes(): List<RuneEntity>
    fun getItems(): List<ItemEntity>
    fun insert(championEntities: ChampionRuneItemSpell)
    fun delete()

    fun getSpells(): List<SpellEntity>
}
