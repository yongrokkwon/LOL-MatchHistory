package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import gg.op.lol.data.models.SpellEntity

@Dao
interface SpellDao {
    @Insert
    fun insert(spell: SpellEntity)

    @Query("SELECT * FROM spell WHERE `key` = :key")
    fun findByKey(key: String): SpellEntity?

    @Query("SELECT * FROM spell")
    fun getAll(): List<SpellEntity>
}
