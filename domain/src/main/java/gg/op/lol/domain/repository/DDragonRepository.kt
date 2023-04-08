package gg.op.lol.domain.repository

import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.models.ChampionRuneItemSpell
import gg.op.lol.domain.models.Item
import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.models.Spell
import kotlinx.coroutines.flow.Flow

interface DDragonRepository {
    // Remote
    suspend fun getRemoteChampions(version: String): List<Champion>
    suspend fun getRemoteSpells(version: String): List<Spell>
    suspend fun getRemoteRunes(version: String): List<Rune>
    suspend fun getRemoteItems(version: String): List<Item>
    suspend fun getVersion(): Flow<String>
    fun insert(championRuneItemSpell: ChampionRuneItemSpell)
    fun delete()

    // Local
    fun getLocalChampions(): Flow<List<Champion>>
    fun getLocalSpells(): Flow<List<Spell>>
    fun getLocalRunes(): Flow<List<Rune>>
    fun getLocalItems(): Flow<List<Item>>
}
