package gg.lol.android.data.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey
    @ColumnInfo(name = "nickname")
    val nickname: String = "",
    @ColumnInfo(name = "icon")
    val icon: String = ""
)
