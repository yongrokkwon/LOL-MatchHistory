package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.data.remote.models.SpellResponse

interface DDragonRemote {
    suspend fun getChampions(version: String): ChampionResponse
    suspend fun getSpells(version: String): SpellResponse
    suspend fun getRunes(version: String): List<RuneResponse>
    suspend fun getItems(version: String): ItemResponse
    suspend fun getVersions(): List<String>
}
