package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.data.local.models.SpellEntity

interface DDragonLocal {
    fun getChampion(id: Int): ChampionEntity?
    fun getChampions(): List<ChampionEntity>
    fun getRunes(): List<RuneEntity>
    fun getItems(): List<ItemEntity>
    fun insertChampion(championEntity: ChampionEntity)
    fun insertSpell(spellEntity: SpellEntity)
    fun insertRune(runeEntity: RuneEntity)
    fun insertItem(itemEntity: ItemEntity)
    fun deleteAllChampion()
    fun deleteAllSpell()
    fun deleteAllRune()
    fun deleteAllItem()

    fun getSpells(): List<SpellEntity>
}
