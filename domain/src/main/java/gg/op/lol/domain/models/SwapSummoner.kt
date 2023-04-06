package gg.op.lol.domain.models

data class SwapSummoner(
    val firstSummonerJoin: SearchHistorySummonerJoin,
    val firstIndex: Int,
    val secondSummonerJoin: SearchHistorySummonerJoin,
    val secondIndex: Int
)
