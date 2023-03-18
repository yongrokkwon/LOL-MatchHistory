package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import gg.op.lol.data.local.models.SummonerLocalEntity

@Dao
interface SummonerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(summoner: SummonerLocalEntity)

    @Query("SELECT * FROM summoner")
    fun getSummoners(): List<SummonerLocalEntity>

    @Query("SELECT * FROM summoner WHERE nickname = :nickName")
    fun getSummonerByNickName(nickName: String): SummonerLocalEntity?

    @Update
    fun update(summoner: SummonerLocalEntity)

    @Delete
    fun delete(summoner: SummonerLocalEntity)
}