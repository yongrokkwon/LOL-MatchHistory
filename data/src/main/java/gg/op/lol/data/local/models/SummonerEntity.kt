package gg.op.lol.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "summoner")
data class SummonerEntity(
    @PrimaryKey
    @ColumnInfo(name = "summonerName")
    val summonerName: String,
    @ColumnInfo(name = "profileIconId")
    val profileIconId: Int,
    @ColumnInfo(name = "tier")
    val tier: String,
    @ColumnInfo(name = "rank")
    val rank: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "mySummoner")
    val mySummoner: Boolean = false
)
