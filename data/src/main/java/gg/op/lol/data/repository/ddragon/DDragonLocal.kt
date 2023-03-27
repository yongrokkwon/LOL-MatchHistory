package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.models.ItemEntity
import gg.op.lol.data.models.RuneEntity
import gg.op.lol.data.models.SpellEntity

interface DDragonLocal {
    fun getChampion(key: String): ChampionEntity?
    fun getChampions(): List<ChampionEntity>
    fun getRunes(): List<RuneEntity>
    fun getItems(): List<ItemEntity>
    fun insertChampion(championEntity: ChampionEntity)
    fun insertSpell(spellEntity: SpellEntity)
    fun insertRune(runeEntity: RuneEntity)
    fun insertItem(itemEntity: ItemEntity)

    fun getSpells(): List<SpellEntity>
}