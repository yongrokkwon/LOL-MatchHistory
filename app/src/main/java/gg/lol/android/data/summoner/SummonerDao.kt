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
    fun getAll(): List<Summoner>

    @Query("SELECT * FROM summoner WHERE favorite = 1")
    fun getFavorites(): List<Summoner>

    @Query("SELECT * FROM summoner WHERE summoner = 1")
    fun getMySummoners(): List<Summoner>

    @Update
    fun update(summoner: Summoner)

    @Delete
    fun delete(summoner: Summoner)
}