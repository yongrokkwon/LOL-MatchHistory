package gg.op.lol.data.source.ddragon

import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.data.remote.models.SpellResponse
import gg.op.lol.data.repository.ddragon.DDragonDataSource
import gg.op.lol.data.repository.ddragon.DDragonRemote
import javax.inject.Inject

class DDragonRemoteDataSource @Inject constructor(
    private val ddragonRemote: DDragonRemote
) : DDragonDataSource {

    suspend fun getRemoteChampions(version: String): ChampionResponse =
        ddragonRemote.getChampions(version)

    suspend fun getRemoteSpells(version: String): SpellResponse =
        ddragonRemote.getSpells(version)

    suspend fun getRemoteRunes(version: String): List<RuneResponse> =
        ddragonRemote.getRunes(version)

    suspend fun getRemoteItems(version: String): ItemResponse =
        ddragonRemote.getItems(version)

    suspend fun getVersions(): List<String> = ddragonRemote.getVersions()
}
