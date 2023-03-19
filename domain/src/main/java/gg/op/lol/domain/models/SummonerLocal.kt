package gg.op.lol.domain.models

data class SummonerLocal(
    val nickName: String,
    val isFavorite: Boolean = false,
    val mySummoner: Boolean = false
)