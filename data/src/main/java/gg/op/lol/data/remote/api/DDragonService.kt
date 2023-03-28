package gg.op.lol.data.remote.api

import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.data.remote.models.SpellResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DDragonService {
    @GET("cdn/{version}/data/ko_KR/champion.json")
    suspend fun getChampions(@Path("version") version: String): ChampionResponse

    @GET("cdn/{version}/data/ko_KR/summoner.json")
    suspend fun getSpells(@Path("version") version: String): SpellResponse

    @GET("cdn/{version}/data/ko_KR/runesReforged.json")
    suspend fun getRunes(@Path("version") version: String): List<RuneResponse>

    @GET("cdn/{version}/data/ko_KR/item.json")
    suspend fun getItems(@Path("version") version: String): ItemResponse

    @GET("api/versions.json")
    suspend fun getVersions(): List<String>
}
