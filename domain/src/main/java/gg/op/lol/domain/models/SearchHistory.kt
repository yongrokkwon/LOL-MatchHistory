package gg.op.lol.domain.models

data class SearchHistory(
    val summonerName: String,
    val profileIconId: Int,
    val tier: Tier,
    val lastSearchedAt: Long
)
