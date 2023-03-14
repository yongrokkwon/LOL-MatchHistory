package gg.lol.android.network.response

data class SummonerResponse(
    val code: Int,
    val message: String,
    val result: Result
) {
    val isSuccessful: Boolean
        get() = code == 200

    data class Result(
        val leaguePoints: String,
        val losses: String,
        val name: String,
        val profileIconId: Int,
        val queueType: String,
        val summonerLevel: Int,
        val tier: String,
        val wins: String
    )
}