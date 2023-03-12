package gg.lol.android.data.summoner

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SummonerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(summoner: Summoner)

    @Query("SELECT * FROM summoner")
    fun fetchSummoners(): List<Summoner>

    @Query("SELECT * FROM summoner WHERE nickname = :nickName")
    fun getSummonerByNickName(nickName: String): Summoner?

    @Update
    fun update(summoner: Summoner)

    @Delete
    fun delete(summoner: Summoner)
}