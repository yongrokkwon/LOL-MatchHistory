package gg.op.lol.data.remote.models

class SummonerHistoryResponse : ArrayList<SummonerHistoryResponse.Item>() {
    data class Item(
        val freshBlood: Boolean = false,
        val hotStreak: Boolean = false,
        val inactive: Boolean = false,
        val leagueId: String = "",
        val leaguePoints: Int = 0,
        val losses: Int = 0,
        val queueType: String = "RANKED_FLEX_SR",
        val rank: String = "",
        val summonerId: String = "",
        val summonerName: String = "",
        val tier: String = "Unranked",
        val veteran: Boolean = false,
        val wins: Int = 0
    )
}
