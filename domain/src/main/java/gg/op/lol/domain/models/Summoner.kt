package gg.op.lol.domain.models

data class Summoner(
    var summonerLevel: Int = 0,
    var summonerName: String = "",
    var puuid: String = "",
    var profileIconId: Int = 0,
    val histories: List<Item>
) {
    data class Item(
        val freshBlood: Boolean,
        val hotStreak: Boolean,
        val inactive: Boolean,
        val leagueId: String,
        val leaguePoints: Int,
        val losses: Int,
        val queueType: String,
        val rank: String,
        val summonerId: String,
        val summonerName: String,
        val tier: String,
        val veteran: Boolean,
        val wins: Int
    )
}
