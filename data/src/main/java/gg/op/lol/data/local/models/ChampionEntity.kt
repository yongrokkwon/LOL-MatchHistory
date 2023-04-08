package gg.op.lol.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champion")
data class ChampionEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imagePath: String
)
