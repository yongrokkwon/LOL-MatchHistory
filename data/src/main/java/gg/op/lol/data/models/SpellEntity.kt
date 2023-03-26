package gg.op.lol.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spell")
data class SpellEntity(
    @PrimaryKey val key: String,
    @ColumnInfo(name = "imagePath") val imagePath: String
)
