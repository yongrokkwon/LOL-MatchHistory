package gg.op.lol.domain.repository

import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.models.Item
import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.models.Spell
import kotlinx.coroutines.flow.Flow

interface DDragonRepository {
    // Remote
    suspend fun getRemoteChampions(): Flow<List<Champion?>>
    suspend fun getRemoteSpells(): Flow<List<Spell?>>
    suspend fun getRemoteRunes(): Flow<List<Rune?>>
    suspend fun getRemoteItems(): Flow<List<Item?>>

    // Local
    fun getLocalChampions(): Flow<List<Champion>>
    fun getLocalSpells(): Flow<List<Spell>>
    fun getLocalRunes(): Flow<List<Rune>>
    fun getLocalItems(): Flow<List<Item>>
}
