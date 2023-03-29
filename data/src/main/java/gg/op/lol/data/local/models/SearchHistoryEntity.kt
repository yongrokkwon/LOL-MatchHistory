package gg.op.lol.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "nickname")
    val nickname: String = "",
    @ColumnInfo(name = "icon")
    val icon: Int = 0
)
