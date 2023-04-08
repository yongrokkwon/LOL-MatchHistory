package gg.op.lol.domain.models

data class MySummoner(
    val summonerName: String,
    val summonerLevel: Int,
    val profileIconId: Int,
    val tier: Tier,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int,
    val winRate: Int,
    val kda: Double,
    val champion: List<Pair<Int, Champion>>
) {

    data class Champion(
        val imagePath: String,
        val winRate: Int,
        val kda: Double
    )
}
