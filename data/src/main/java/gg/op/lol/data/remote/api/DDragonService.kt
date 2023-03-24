package gg.op.lol.data.remote.api

import gg.op.lol.data.remote.models.ChampionResponse
import retrofit2.http.GET

interface DDragonService {
    @GET("cdn/13.6.1/data/ko_KR/champion.json")
    suspend fun getChampions(): ChampionResponse
}
