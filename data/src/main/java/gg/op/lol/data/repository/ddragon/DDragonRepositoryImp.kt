package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.mapper.ChampionEntityMapper
import gg.op.lol.data.mapper.ChampionResponseMapper
import gg.op.lol.data.mapper.ItemEntityMapper
import gg.op.lol.data.mapper.ItemResponseMapper
import gg.op.lol.data.mapper.RuneEntityMapper
import gg.op.lol.data.mapper.RuneResponseMapper
import gg.op.lol.data.mapper.SpellEntityMapper
import gg.op.lol.data.mapper.SpellResponseMapper
import gg.op.lol.data.source.ddragon.DDragonDataSourceFactory
import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.models.ChampionRuneItemSpell
import gg.op.lol.domain.models.Item
import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.models.Spell
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class DDragonRepositoryImp @Inject constructor(
    private val ddragonDataSourceFactory: DDragonDataSourceFactory,
    private val championResponseMapper: ChampionResponseMapper,
    private val championEntityMapper: ChampionEntityMapper,
    private val spellResponseMapper: SpellResponseMapper,
    private val spellEntityMapper: SpellEntityMapper,
    private val runeResponseMapper: RuneResponseMapper,
    private val runeEntityMapper: RuneEntityMapper,
    private val itemResponseMapper: ItemResponseMapper,
    private val itemEntityMapper: ItemEntityMapper
) : DDragonRepository {
    override fun delete() {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        local.delete()
    }

    override fun insert(championRuneItemSpell: ChampionRuneItemSpell) {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        local.insert(championRuneItemSpell)
    }

    override suspend fun getVersion(): Flow<String> = flow {
        val latestVersion = ddragonDataSourceFactory.getRemoteDataSource().getVersions().first()
        emit(latestVersion)
    }

    override suspend fun getRemoteChampions(version: String): List<Champion> {
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val championResponse = remote.getRemoteChampions(version)
        return championResponse.data.map { championResponseMapper.mapFromEntity(it.value) }
    }

    override suspend fun getRemoteSpells(version: String): List<Spell> {
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val remoteChampions = remote.getRemoteSpells(version)
        return remoteChampions.data.map { spellResponseMapper.mapFromEntity(it.value) }
    }

    override suspend fun getRemoteRunes(version: String): List<Rune> {
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val remoteRunes = remote.getRemoteRunes(version)
        return remoteRunes.map { runeResponseMapper.mapFromEntity(it) }
    }

    override suspend fun getRemoteItems(version: String): List<Item> {
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val remoteItems = remote.getRemoteItems(version)
        return remoteItems.data.map { itemResponseMapper.mapFromEntity(it.value, it.key) }
    }

    override fun getLocalSpells(): Flow<List<Spell>> = flow {
        val localSpells = ddragonDataSourceFactory.getLocalDataSource().getLocalSpells()
        emit(localSpells.map { spellEntityMapper.mapFromEntity(it) })
    }

    override fun getLocalChampions(): Flow<List<Champion>> = flow {
        val localChampions = ddragonDataSourceFactory.getLocalDataSource().getLocalChampions()
        emit(localChampions.map { championEntityMapper.mapFromEntity(it) })
    }

    override fun getLocalItems(): Flow<List<Item>> = flow {
        val localItems = ddragonDataSourceFactory.getLocalDataSource().getLocalItems()
        emit(localItems.map { itemEntityMapper.mapFromEntity(it) })
    }

    override fun getLocalRunes(): Flow<List<Rune>> = flow {
        val localRunes = ddragonDataSourceFactory.getLocalDataSource().getLocalRunes()
        emit(localRunes.map { runeEntityMapper.mapFromEntity(it) })
    }
}
