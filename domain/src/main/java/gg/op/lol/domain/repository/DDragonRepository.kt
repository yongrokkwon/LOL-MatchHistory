package gg.op.lol.domain.repository

import gg.op.lol.domain.models.Champion
import kotlinx.coroutines.flow.Flow

interface DDragonRepository {
    // Remote
    suspend fun getChampions(): Flow<List<Champion?>>
}
