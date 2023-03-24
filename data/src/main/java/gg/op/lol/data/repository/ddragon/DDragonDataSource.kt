package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.remote.models.ChampionResponse

interface DDragonDataSource {
    // Remote
    suspend fun getRemoteChampions(): ChampionResponse

    // Local
    fun insertChampion(championEntity: ChampionEntity)
    fun getChampion(key: String): ChampionEntity?
    fun getLocalChampions(): List<ChampionEntity>
}
