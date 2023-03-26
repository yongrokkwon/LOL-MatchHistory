package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.models.ItemEntity
import gg.op.lol.data.models.RuneEntity
import gg.op.lol.data.models.SpellEntity
import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.data.remote.models.SpellResponse

interface DDragonDataSource {
    // Remote
    suspend fun getRemoteChampions(): ChampionResponse
    suspend fun getRemoteSpells(): SpellResponse
    suspend fun getRemoteRunes(): List<RuneResponse>
    suspend fun getRemoteItems(): ItemResponse

    // Local
    fun insertChampion(championEntity: ChampionEntity)
    fun insertSpell(spellEntity: SpellEntity)
    fun insertRune(runeEntity: RuneEntity)
    fun insertItem(itemEntity: ItemEntity)
    fun getChampion(key: String): ChampionEntity?
    fun getLocalChampions(): List<ChampionEntity>
    fun getLocalRunes(): List<RuneEntity>
    fun getLocalItems(): List<ItemEntity>
    fun getLocalSpells(): List<SpellEntity>
}
