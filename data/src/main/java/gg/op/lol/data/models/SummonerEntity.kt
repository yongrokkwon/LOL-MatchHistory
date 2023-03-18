package gg.op.lol.data.models;

data class SummonerEntity(
    val nickName: String,
    val isFavorite: Boolean = false,
    val mySummoner: Boolean = false
)