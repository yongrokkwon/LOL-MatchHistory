package gg.op.lol.domain.models

data class SearchHistorySummonerJoin(
    val summonerName: String,
    val profileIconId: Int,
    val tier: Tier,
    val lastSearchedAt: Long,
    val isFavorite: Boolean,
    val mySummoner: Boolean
)
