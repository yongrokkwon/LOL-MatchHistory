package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import gg.op.lol.data.local.models.ItemEntity

@Dao
interface ItemDao {
    @Insert
    fun insert(item: ItemEntity)

    @Query("SELECT * FROM item WHERE `id` = :id")
    fun findById(id: String): ItemEntity?

    @Query("SELECT * FROM item")
    fun getAll(): List<ItemEntity>
}
