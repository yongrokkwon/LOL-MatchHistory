package gg.op.lol.domain.models

data class Summoner(
    val summonerLevel: Int,
    val summonerName: String,
    val puuid: String,
    val profileIconId: Int,
    val histories: List<Body>
) {
    data class Body(
        val freshBlood: Boolean,
        val hotStreak: Boolean,
        val inactive: Boolean,
        val leagueId: String,
        val leaguePoints: Int,
        val losses: Int,
        val queueType: QueueType,
        val rank: String,
        val summonerId: String,
        val summonerName: String,
        val tier: String,
        val veteran: Boolean,
        val wins: Int
    )
}
