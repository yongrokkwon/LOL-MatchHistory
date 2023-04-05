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
        "SELECT a.summoner_level, a.summoner_name, a.profile_icon_id, a.tier, a.rank, a.last_searched_at," + '\n' + // ktlint-disable max-line-length
            "b.is_favorite, b.my_summoner, b.summoner_level, b.favorite_order" + '\n' +
            "FROM search_history a" + '\n' +
            "LEFT JOIN summoner b" + '\n' +
            "ON a.summoner_name = b.summoner_name"
    )
    fun getSearchHistoryWithFavorites(): List<SearchHistorySummonerJoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(searchHistory: SearchHistoryEntity)

    @Delete
    fun delete(searchHistory: List<SearchHistoryEntity>): Int
}
