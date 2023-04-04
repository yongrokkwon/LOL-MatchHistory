package gg.op.lol.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistorySummonerJoinEntity(
    @PrimaryKey
    @ColumnInfo(name = "summoner_name")
    val summonerName: String,
    @ColumnInfo(name = "summoner_level")
    val summonerLevel: Int,
    @ColumnInfo(name = "profile_icon_id")
    val profileIconId: Int,
    @ColumnInfo(name = "tier")
    val tier: String,
    @ColumnInfo(name = "rank")
    val rank: String,
    @ColumnInfo(name = "last_searched_at")
    val lastSearchedAt: Long,
    @ColumnInfo(name = "favorite")
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "my_summoner")
    val mySummoner: Boolean = false
)
