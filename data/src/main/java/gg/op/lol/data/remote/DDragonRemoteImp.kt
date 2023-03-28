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

    override suspend fun getChampions(version: String): ChampionResponse =
        ddragonService.getChampions(version)

    override suspend fun getSpells(version: String): SpellResponse =
        ddragonService.getSpells(version)

    override suspend fun getItems(version: String): ItemResponse = ddragonService.getItems(version)
    override suspend fun getRunes(version: String): List<RuneResponse> =
        ddragonService.getRunes(version)

    override suspend fun getVersions(): List<String> = ddragonService.getVersions()
}
