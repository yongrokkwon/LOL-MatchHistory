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
    fun update(summoner: SummonerEntity)

    @Query("SELECT * FROM summoner")
    fun getSummoners(): List<SummonerEntity>

    @Query("SELECT * FROM summoner WHERE summonerName = :summonerName")
    fun getSummonerByNickName(summonerName: String): SummonerEntity?

    @Delete
    fun delete(summoner: SummonerEntity)
}
