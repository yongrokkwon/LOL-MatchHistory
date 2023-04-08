package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gg.op.lol.data.local.models.ChampionEntity

@Dao
interface ChampionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(championEntities: List<ChampionEntity>)

    @Query("SELECT * FROM champion WHERE id = :id")
    fun findByKey(id: Int): ChampionEntity?

    @Query("SELECT * FROM champion")
    fun getAll(): List<ChampionEntity>

    @Query("DELETE FROM champion")
    fun deleteAll()
}
