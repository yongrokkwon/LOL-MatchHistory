package gg.op.lol.data.models

data class SummonerHistoryModel(
    val summonerLevel: Int = 0,
    val item: List<Item>
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
