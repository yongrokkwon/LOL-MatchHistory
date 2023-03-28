package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.data.local.models.SpellEntity
import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.data.remote.models.SpellResponse

interface DDragonDataSource {
    // Remote
    suspend fun getRemoteChampions(version: String): ChampionResponse
    suspend fun getRemoteSpells(version: String): SpellResponse
    suspend fun getRemoteRunes(version: String): List<RuneResponse>
    suspend fun getRemoteItems(version: String): ItemResponse
    suspend fun getVersions(): List<String>

    // Local
    fun deleteAllChampion()
    fun deleteAllSpell()
    fun deleteAllRune()
    fun deleteAllItem()
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
