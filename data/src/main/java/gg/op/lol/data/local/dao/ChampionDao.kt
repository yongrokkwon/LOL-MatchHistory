package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import gg.op.lol.data.local.models.ChampionEntity

@Dao
interface ChampionDao {
    @Insert
    fun insert(champion: ChampionEntity)

    @Query("SELECT * FROM champion WHERE `key` = :key")
    fun findByKey(key: String): ChampionEntity?

    @Query("SELECT * FROM champion")
    fun getAll(): List<ChampionEntity>

    @Query("DELETE FROM champion")
    fun deleteAll()
}
