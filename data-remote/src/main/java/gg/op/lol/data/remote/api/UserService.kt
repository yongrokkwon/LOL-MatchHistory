package gg.op.lol.data.remote.api

import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.remote.models.SummonerInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummonerInfo(@Path("summonerName") summonerName: String): SummonerInfoResponse

    @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    suspend fun getSummonerHistory(@Path("encryptedSummonerId") encryptedSummonerId: String): SummonerHistoryResponse
}
