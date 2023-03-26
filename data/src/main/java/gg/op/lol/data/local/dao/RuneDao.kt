package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import gg.op.lol.data.models.RuneEntity

@Dao
interface RuneDao {
    @Insert
    fun insert(item: RuneEntity)

    @Query("SELECT * FROM rune WHERE `id` = :id")
    fun findById(id: Int): RuneEntity?

    @Query("SELECT * FROM rune")
    fun getAll(): List<RuneEntity>
}
