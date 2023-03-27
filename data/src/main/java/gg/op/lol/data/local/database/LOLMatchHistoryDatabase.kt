package gg.op.lol.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import gg.op.lol.data.local.dao.ChampionDao
import gg.op.lol.data.local.dao.ItemDao
import gg.op.lol.data.local.dao.RuneDao
import gg.op.lol.data.local.dao.SpellDao
import gg.op.lol.data.local.dao.SummonerDao
import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.models.ItemEntity
import gg.op.lol.data.models.RuneEntity
import gg.op.lol.data.models.SpellEntity
import gg.op.lol.data.models.SummonerEntity

const val DATABASE_NAME = "LOL-MatchHistory-DB"

@Database(
    entities = [
        SummonerEntity::class,
        ChampionEntity::class,
        SpellEntity::class,
        RuneEntity::class,
        ItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LOLMatchHistoryDatabase : RoomDatabase() {
    abstract fun summonerDao(): SummonerDao
    abstract fun championDao(): ChampionDao
    abstract fun spellDao(): SpellDao
    abstract fun itemDao(): ItemDao
    abstract fun runeDao(): RuneDao

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
                .build()
        }
    }
}
