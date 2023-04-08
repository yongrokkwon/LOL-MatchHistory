package gg.op.lol.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spell")
data class SpellEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "imagePath") val imagePath: String
)
