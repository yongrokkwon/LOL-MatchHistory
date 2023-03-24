package gg.op.lol.data.source.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.repository.ddragon.DDragonDataSource
import gg.op.lol.data.repository.ddragon.DDragonRemote
import javax.inject.Inject

class DDragonRemoteDataSource @Inject constructor(
    private val dDragonRemote: DDragonRemote
) : DDragonDataSource {

    override suspend fun getRemoteChampions(): ChampionResponse = dDragonRemote.getChampions()

    override fun getLocalChampions(): List<ChampionEntity> {
        throw UnsupportedOperationException(
            "getLocalChampions is not supported for RemoteDataSource."
        )
    }

    override fun getChampion(key: String): ChampionEntity? {
        throw UnsupportedOperationException(
            "getChampion is not supported for RemoteDataSource."
        )
    }

    override fun insertChampion(championEntity: ChampionEntity) {
        throw UnsupportedOperationException(
            "insertChampion is not supported for RemoteDataSource."
        )
    }
}
