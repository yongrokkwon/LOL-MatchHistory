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

    @Query("SELECT MAX(favorite_order) FROM summoner")
    fun getMaxFavoriteOrder(): Int

    @Query(
        "UPDATE summoner SET favorite_order = :favoriteOrder WHERE summoner_name = :summonerName"
    )
    fun updateOrderById(summonerName: String, favoriteOrder: Int): Int

    @Query(
        "UPDATE summoner SET favorite_order = CASE " +
            "WHEN summoner_name = :summonerName1 THEN :favoriteOrder1 " +
            "WHEN summoner_name = :summonerName2 THEN :favoriteOrder2 " +
            "ELSE favorite_order END " +
            "WHERE summoner_name IN (:summonerName1, :summonerName2)"
    )
    fun swapFavoriteOrder(
        summonerName1: String,
        favoriteOrder1: Int,
        summonerName2: String,
        favoriteOrder2: Int
    )

    @Delete
    fun delete(summoner: SummonerEntity)

    fun updateFavoriteOrderToNextById(summoner: SummonerEntity) {
        val entity = getMaxFavoriteOrder()
        val favoriteOrder = entity.plus(1)
        updateOrderById(summoner.summonerName, favoriteOrder)
    }
}
