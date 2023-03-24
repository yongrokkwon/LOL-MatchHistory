package gg.op.lol.data.remote

import gg.op.lol.data.remote.api.DDragonService
import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.repository.ddragon.DDragonRemote
import javax.inject.Inject

class DDragonRemoteImp @Inject constructor(
    private val ddragonService: DDragonService
) : DDragonRemote {

    override suspend fun getChampions(): ChampionResponse = ddragonService.getChampions()
}
