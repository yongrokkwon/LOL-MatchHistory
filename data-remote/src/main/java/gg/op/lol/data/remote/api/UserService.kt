package gg.op.lol.data.remote.api

import gg.op.lol.data.remote.models.SummonerResponseModel
import retrofit2.http.GET

interface UserService {
    @GET("/v1/summoner")
    suspend fun getSummoner(nickName: String): SummonerResponseModel
}