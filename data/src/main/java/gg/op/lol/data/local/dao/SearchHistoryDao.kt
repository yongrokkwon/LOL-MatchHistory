package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gg.op.lol.data.local.models.SearchHistoryEntity
import gg.op.lol.data.local.models.SearchHistorySummonerJoinEntity

@Dao
interface SearchHistoryDao {
    @Query(
        "SELECT a.*, b.favorite, b.my_summoner" + '\n' +
            "FROM search_history a" + '\n' +
            "LEFT JOIN summoner b" + '\n' +
            "ON a.summoner_name = b.summoner_name"
    )
    fun getSearchHistory(): List<SearchHistorySummonerJoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchHistory(searchHistory: SearchHistoryEntity)

    @Delete
    fun delete(searchHistory: List<SearchHistoryEntity>): Int
}
