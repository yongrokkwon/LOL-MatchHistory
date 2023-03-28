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
import gg.op.lol.data.repository.ddragon.DDragonRemote
import javax.inject.Inject

class DDragonRemoteDataSource @Inject constructor(
    private val ddragonRemote: DDragonRemote
) : DDragonDataSource {

    override suspend fun getRemoteChampions(version: String): ChampionResponse =
        ddragonRemote.getChampions(version)

    override suspend fun getRemoteSpells(version: String): SpellResponse =
        ddragonRemote.getSpells(version)

    override suspend fun getRemoteRunes(version: String): List<RuneResponse> =
        ddragonRemote.getRunes(version)

    override suspend fun getRemoteItems(version: String): ItemResponse =
        ddragonRemote.getItems(version)

    override suspend fun getVersions(): List<String> = ddragonRemote.getVersions()

    override fun insertRune(runeEntity: RuneEntity) {
        throw UnsupportedOperationException(
            "insertRune is not supported for RemoteDataSource."
        )
    }

    override fun insertItem(itemEntity: ItemEntity) {
        throw UnsupportedOperationException(
            "insertItem is not supported for RemoteDataSource."
        )
    }

    override fun getLocalRunes(): List<RuneEntity> {
        throw UnsupportedOperationException(
            "getLocalRunes is not supported for RemoteDataSource."
        )
    }

    override fun getLocalItems(): List<ItemEntity> {
        throw UnsupportedOperationException(
            "getLocalItems is not supported for RemoteDataSource."
        )
    }

    override fun insertSpell(spellEntity: SpellEntity) {
        throw UnsupportedOperationException(
            "insertSpell is not supported for RemoteDataSource."
        )
    }

    override fun getLocalSpells(): List<SpellEntity> {
        throw UnsupportedOperationException(
            "getLocalSpells is not supported for RemoteDataSource."
        )
    }

    override fun getLocalChampions(): List<ChampionEntity> {
        throw UnsupportedOperationException(
            "getLocalChampions is not supported for RemoteDataSource."
        )
    }

    override fun getChampion(key: String): ChampionEntity? {
        throw UnsupportedOperationException(
            "getChampion is not supported for RemoteDataSource."
        )
    }

    override fun insertChampion(championEntity: ChampionEntity) {
        throw UnsupportedOperationException(
            "insertChampion is not supported for RemoteDataSource."
        )
    }

    override fun deleteAllChampion() {
        throw UnsupportedOperationException(
            "deleteAllChampion is not supported for RemoteDataSource."
        )
    }

    override fun deleteAllSpell() {
        throw UnsupportedOperationException(
            "deleteAllSpell is not supported for RemoteDataSource."
        )
    }

    override fun deleteAllRune() {
        throw UnsupportedOperationException(
            "deleteAllRune is not supported for RemoteDataSource."
        )
    }

    override fun deleteAllItem() {
        throw UnsupportedOperationException(
            "deleteAllItem is not supported for RemoteDataSource."
        )
    }
}
