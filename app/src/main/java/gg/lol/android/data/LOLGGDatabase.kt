package gg.lol.android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gg.lol.android.data.search.SearchHistory
import gg.lol.android.data.search.SearchHistoryDao
import gg.lol.android.data.summoner.Summoner
import gg.lol.android.data.summoner.SummonerDao

const val DATABASE_NAME = "lolgg-db"

@Database(entities = [Summoner::class, SearchHistory::class], version = 1, exportSchema = false)
abstract class LOLGGDatabase : RoomDatabase() {
    abstract fun summonerDao(): SummonerDao
    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: LOLGGDatabase? = null

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
