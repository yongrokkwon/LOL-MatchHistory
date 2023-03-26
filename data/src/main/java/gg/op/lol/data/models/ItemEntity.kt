package gg.op.lol.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "full") val full: String,
    @ColumnInfo(name = "group") val group: String
)
