package gg.lol.android.data.summoner;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "summoner")
data class Summoner(
    @PrimaryKey
    @ColumnInfo(name = "nickname")
    val nickname: String,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean,
    @ColumnInfo(name = "summoner")
    val summoner: Boolean
)