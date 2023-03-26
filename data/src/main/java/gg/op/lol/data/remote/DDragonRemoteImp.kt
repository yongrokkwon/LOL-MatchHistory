package gg.op.lol.data.remote

import gg.op.lol.data.remote.api.DDragonService
import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.data.remote.models.SpellResponse
import gg.op.lol.data.repository.ddragon.DDragonRemote
import javax.inject.Inject

class DDragonRemoteImp @Inject constructor(
    private val ddragonService: DDragonService
) : DDragonRemote {

    override suspend fun getChampions(): ChampionResponse = ddragonService.getChampions()
    override suspend fun getSpells(): SpellResponse = ddragonService.getSpells()
    override suspend fun getItems(): ItemResponse = ddragonService.getItems()
    override suspend fun getRunes(): List<RuneResponse> = ddragonService.getRunes()
}
