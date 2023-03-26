package gg.op.lol.data.remote.api

import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.data.remote.models.SpellResponse
import retrofit2.http.GET

interface DDragonService {
    @GET("cdn/13.6.1/data/ko_KR/champion.json")
    suspend fun getChampions(): ChampionResponse

    @GET("cdn/13.6.1/data/ko_KR/summoner.json")
    suspend fun getSpells(): SpellResponse

    @GET("cdn/13.6.1/data/ko_KR/runesReforged.json")
    suspend fun getRunes(): List<RuneResponse>

    @GET("cdn/13.6.1/data/ko_KR/item.json")
    suspend fun getItems(): ItemResponse
}
