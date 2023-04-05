package gg.op.lol.data.local.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import gg.op.lol.BuildConfig
import gg.op.lol.data.local.dao.ChampionDao
import gg.op.lol.data.local.dao.ItemDao
import gg.op.lol.data.local.dao.RuneDao
import gg.op.lol.data.local.dao.SearchHistoryDao
import gg.op.lol.data.local.dao.SpellDao
import gg.op.lol.data.local.dao.SummonerDao
import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.data.local.models.SearchHistoryEntity
import gg.op.lol.data.local.models.SpellEntity
import gg.op.lol.data.local.models.SummonerEntity
import java.util.concurrent.Executors

const val DATABASE_NAME = "LOL-MatchHistory-DB"

@Database(
    entities = [
        SummonerEntity::class,
        ChampionEntity::class,
        SpellEntity::class,
        RuneEntity::class,
        ItemEntity::class,
        SearchHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RuneConvert::class)
abstract class LOLMatchHistoryDatabase : RoomDatabase() {
    abstract fun championDao(): ChampionDao
    abstract fun spellDao(): SpellDao
    abstract fun itemDao(): ItemDao
    abstract fun runeDao(): RuneDao
    abstract fun summonerDao(): SummonerDao
    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: LOLMatchHistoryDatabase? = null

        fun getInstance(context: Context): LOLMatchHistoryDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LOLMatchHistoryDatabase {
            return Room.databaseBuilder(context, LOLMatchHistoryDatabase::class.java, DATABASE_NAME)
                .setQueryCallback(
                    { sqlQuery, bindArgs ->
                        if (BuildConfig.DEBUG) {
                            Log.d("## RoomDB Query", sqlQuery)
                            if (bindArgs.isNotEmpty()) {
                                Log.d("## RoomDB Args", bindArgs.toString())
                            }
                        }
                    },
                    Executors.newCachedThreadPool()
                ).build()
        }
    }
}
