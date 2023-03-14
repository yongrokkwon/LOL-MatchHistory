package gg.lol.android.network

import gg.lol.android.network.response.SummonerResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("/v1/summoner")
    suspend fun getSummoner(name: String): Response<SummonerResponse>
}