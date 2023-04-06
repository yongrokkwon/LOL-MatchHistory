package gg.op.lol.domain.models

data class SearchHistorySummonerJoin(
    val summonerName: String,
    val summonerLevel: Int,
    val profileIconId: Int,
    val tier: Tier,
    val lastSearchedAt: Long,
    val favoriteOrder: Int,
    val isFavorite: Boolean,
    val mySummoner: Boolean
)
