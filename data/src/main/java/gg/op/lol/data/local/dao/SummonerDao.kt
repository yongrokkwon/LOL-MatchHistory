package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gg.op.lol.data.local.models.SummonerEntity

@Dao
interface SummonerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(summoner: SummonerEntity)

    @Query("SELECT * FROM summoner")
    fun getFavorites(): List<SummonerEntity>

    @Query("SELECT * FROM summoner WHERE summoner_name = :summonerName")
    fun getBySummonerName(summonerName: String): SummonerEntity

    @Query("UPDATE summoner SET favorite_order = :newValue WHERE summoner_name = :summonerName")
    fun updateOrderById(summonerName: String, newValue: Int): Int

    @Delete
    fun delete(summoner: SummonerEntity)

    fun updateIntValueToNextById(summonerName: String) {
        val entity = getBySummonerName(summonerName)
        val newValue = entity.favoriteOrder.plus(1)
        updateOrderById(summonerName, newValue)
    }
}
