package gg.op.lol.data.source.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.repository.ddragon.DDragonDataSource
import gg.op.lol.data.repository.ddragon.DDragonLocal
import javax.inject.Inject

class DDragonLocalDataSource @Inject constructor(
    private val dDragonLocal: DDragonLocal
) : DDragonDataSource {

    override suspend fun getRemoteChampions(): ChampionResponse {
        throw UnsupportedOperationException(
            "getChampions is not supported for LocalDataSource."
        )
    }

    override fun insertChampion(championEntity: ChampionEntity) {
        dDragonLocal.insertChampion(championEntity)
    }

    override fun getChampion(key: String): ChampionEntity? {
        return dDragonLocal.getChampion(key)
    }

    override fun getLocalChampions(): List<ChampionEntity> {
        return dDragonLocal.getChampions()
    }
}
