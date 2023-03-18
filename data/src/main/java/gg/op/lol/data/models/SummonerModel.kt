package gg.op.lol.data.models;

data class SummonerModel(
    val nickName: String,
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