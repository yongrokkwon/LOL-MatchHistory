package gg.op.lol.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gg.op.lol.data.local.dao.ChampionDao
import gg.op.lol.data.local.dao.SummonerDao
import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.SummonerLocalEntity

const val DATABASE_NAME = "lolgg-db"

// @Database(entities = [Summoner::class, SearchHistory::class], version = 1, exportSchema = false)
@Database(
    entities = [SummonerLocalEntity::class, ChampionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LOLGGDatabase : RoomDatabase() {
    abstract fun summonerDao(): SummonerDao
    abstract fun championDao(): ChampionDao
//    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: LOLGGDatabase? = null

        fun getInstance(context: Context): LOLGGDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LOLGGDatabase {
            return Room.databaseBuilder(context, LOLGGDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
