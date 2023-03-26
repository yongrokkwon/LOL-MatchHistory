package gg.op.lol.data.source.ddragon

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.data.models.ItemEntity
import gg.op.lol.data.models.RuneEntity
import gg.op.lol.data.models.SpellEntity
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

    override suspend fun getRemoteChampions(): ChampionResponse = ddragonRemote.getChampions()
    override suspend fun getRemoteSpells(): SpellResponse = ddragonRemote.getSpells()
    override suspend fun getRemoteRunes(): List<RuneResponse> = ddragonRemote.getRunes()
    override suspend fun getRemoteItems(): ItemResponse = ddragonRemote.getItems()

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
}
