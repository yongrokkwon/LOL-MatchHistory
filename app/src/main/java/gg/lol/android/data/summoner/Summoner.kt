package gg.lol.android.data.summoner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "summoner")
data class Summoner(
    @PrimaryKey
    @ColumnInfo(name = "nickname")
    val nickname: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "mySummoner")
    val mySummoner: Boolean = false
)
