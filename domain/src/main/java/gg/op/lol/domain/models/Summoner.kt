package gg.op.lol.domain.models

data class Summoner(
    val summonerName: String,
    val profileIconId: Int,
    val summonerLevel: Int = 0,
    val puuid: String = "",
    val histories: List<TierHistory> = listOf(),

    val isFavorite: Boolean = false,
    val mySummoner: Boolean = false
) {
    data class TierHistory(
        val rank: String,
        val tier: String,
        val queueType: QueueType = QueueType.RANKED_SOLO_5X5,
        val freshBlood: Boolean = false,
        val hotStreak: Boolean = false,
        val inactive: Boolean = false,
        val leagueId: String = "",
        val leaguePoints: Int = 0,
        val wins: Int = 0,
        val losses: Int = 0,
        val summonerId: String = "",
        val summonerName: String = "",
        val veteran: Boolean = false
    )
}
