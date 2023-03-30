package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gg.op.lol.data.local.models.SearchHistoryEntity

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM search_history")
    fun getSearchHistory(): List<SearchHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchHistory(searchHistory: SearchHistoryEntity)

    @Delete
    fun delete(searchHistory: List<SearchHistoryEntity>): Int
}
