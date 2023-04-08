package gg.op.lol.data.source.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.data.local.models.SpellEntity
import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.data.remote.models.SpellResponse
import gg.op.lol.data.repository.ddragon.DDragonDataSource
import gg.op.lol.data.repository.ddragon.DDragonLocal
import javax.inject.Inject

class DDragonLocalDataSource @Inject constructor(
    private val ddragonLocal: DDragonLocal
) : DDragonDataSource {

    override suspend fun getRemoteChampions(version: String): ChampionResponse {
        throw UnsupportedOperationException(
            "getChampions is not supported for LocalDataSource."
        )
    }

    override suspend fun getRemoteSpells(version: String): SpellResponse {
        throw UnsupportedOperationException(
            "getRemoteSpells is not supported for LocalDataSource."
        )
    }

    override suspend fun getRemoteRunes(version: String): List<RuneResponse> {
        throw UnsupportedOperationException(
            "getRemoteRunes is not supported for LocalDataSource."
        )
    }

    override suspend fun getRemoteItems(version: String): ItemResponse {
        throw UnsupportedOperationException(
            "getRemoteItems is not supported for LocalDataSource."
        )
    }

    override suspend fun getVersions(): List<String> {
        throw UnsupportedOperationException(
            "getVersions is not supported for LocalDataSource."
        )
    }

    override fun insertRune(runeEntity: RuneEntity) {
        ddragonLocal.insertRune(runeEntity)
    }

    override fun insertItem(itemEntity: ItemEntity) {
        ddragonLocal.insertItem(itemEntity)
    }

    override fun getLocalRunes(): List<RuneEntity> {
        return ddragonLocal.getRunes()
    }

    override fun getLocalItems(): List<ItemEntity> {
        return ddragonLocal.getItems()
    }

    override fun insertSpell(spellEntity: SpellEntity) {
        ddragonLocal.insertSpell(spellEntity)
    }

    override fun insertChampion(championEntity: ChampionEntity) {
        ddragonLocal.insertChampion(championEntity)
    }

    override fun getChampion(id: Int): ChampionEntity? {
        return ddragonLocal.getChampion(id)
    }

    override fun getLocalChampions(): List<ChampionEntity> {
        return ddragonLocal.getChampions()
    }

    override fun getLocalSpells(): List<SpellEntity> {
        return ddragonLocal.getSpells()
    }

    override fun deleteAllChampion() {
        ddragonLocal.deleteAllChampion()
    }

    override fun deleteAllSpell() {
        ddragonLocal.deleteAllSpell()
    }

    override fun deleteAllRune() {
        ddragonLocal.deleteAllRune()
    }

    override fun deleteAllItem() {
        ddragonLocal.deleteAllItem()
    }
}
