package gg.op.lol.domain.models

data class Summoner(
    val nickName: String,
    // Local
    val isFavorite: Boolean = false,
    val mySummoner: Boolean = false,
    // Remote
    val profileIconId: Int = 0,
    val rankInfo: List<RankInfo> = emptyList(),
    val summonerLevel: Int = 0
) {
    data class RankInfo(
        val leagueId: String,
        val leaguePoints: String,
        val losses: String,
        val queueType: String,
        val queueTypeNm: Int,
        val rank: String,
        val tier: String,
        val wins: String
    )
}