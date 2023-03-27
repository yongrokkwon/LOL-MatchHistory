package gg.op.lol.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rune")
data class RuneEntity(
    @ColumnInfo(name = "icon") val icon: String,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "body") val slots: List<Body>
) {
    data class Body(
        @ColumnInfo(name = "icon") val icon: String,
        @ColumnInfo(name = "id") val id: Int
    )
}
