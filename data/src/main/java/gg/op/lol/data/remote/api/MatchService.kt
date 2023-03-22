package gg.op.lol.data.remote.api

import gg.op.lol.data.remote.models.MatchHistoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchService {
    @GET("lol/match/v5/matches/by-puuid/{puuid}/ids")
    suspend fun getMatches(
        @Path("puuid") puuid: String,
        @Query("start") start: Int,
        @Query("count") count: Int
    ): List<String>

    @GET("lol/match/v5/matches/{matchId}")
    suspend fun getMatch(@Path("matchId") matchId: String): MatchHistoryResponse
}
