package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.data.remote.models.SpellResponse

interface DDragonRemote {
    suspend fun getChampions(): ChampionResponse
    suspend fun getSpells(): SpellResponse
    suspend fun getRunes(): List<RuneResponse>
    suspend fun getItems(): ItemResponse
}
