package gg.op.lol.data.remote.models

class SummonerHistoryResponse : ArrayList<SummonerHistoryResponse.Item>() {
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