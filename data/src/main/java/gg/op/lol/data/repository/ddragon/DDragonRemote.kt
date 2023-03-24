package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.remote.models.ChampionResponse

interface DDragonRemote {
    suspend fun getChampions(): ChampionResponse
}
