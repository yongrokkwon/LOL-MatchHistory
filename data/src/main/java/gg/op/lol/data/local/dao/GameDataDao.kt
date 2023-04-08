package gg.op.lol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.data.local.models.SpellEntity

@Dao
interface GameDataDao {

    @Transaction
    fun insertData(
        champion: List<ChampionEntity>,
        rune: List<RuneEntity>,
        item: List<ItemEntity>,
        spell: List<SpellEntity>
    ) {
        insertOrUpdateChampion(champion)
        insertOrUpdateRune(rune)
        insertOrUpdateItem(item)
        insertOrUpdateSpell(spell)
    }

    @Transaction
    fun deleteData() {
        deleteChampion()
        deleteRune()
        deleteItem()
        deleteSpell()
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateChampion(championEntities: List<ChampionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateRune(runeEntities: List<RuneEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateItem(itemEntities: List<ItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateSpell(spellEntities: List<SpellEntity>)

    @Query(value = "DELETE FROM champion")
    fun deleteChampion()

    @Query(value = "DELETE FROM rune")
    fun deleteRune()

    @Query(value = "DELETE FROM item")
    fun deleteItem()

    @Query(value = "DELETE FROM spell")
    fun deleteSpell()
}
