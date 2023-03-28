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
import gg.op.lol.data.source.ddragon.DDragonLocalDataSource
import gg.op.lol.domain.models.Champion
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
    override suspend fun getVersion(): Flow<String> = flow {
        val latestVersion = ddragonDataSourceFactory.getRemoteDataSource().getVersions().first()
        emit(latestVersion)
    }

    override suspend fun getRemoteChampions(version: String): List<Champion> {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val championResponse = remote.getRemoteChampions(version)
        val result = championResponse.data.map { championResponseMapper.mapFromEntity(it.value) }
        local.deleteAllChampion()
        insertLocalChampion(result, local)
        return result
    }

    override suspend fun getRemoteSpells(version: String): List<Spell> {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val remoteChampions = remote.getRemoteSpells(version)
        val result = remoteChampions.data.map { spellResponseMapper.mapFromEntity(it.value) }
        local.deleteAllSpell()
        insertLocalSpell(result, local)
        return result
    }

    override suspend fun getRemoteRunes(version: String): List<Rune> {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val remoteRunes = remote.getRemoteRunes(version)
        val result = remoteRunes.map { runeResponseMapper.mapFromEntity(it) }
        local.deleteAllRune()
        insertLocalRune(result, local)
        return result
    }

    override suspend fun getRemoteItems(version: String): List<Item> {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val remoteItems = remote.getRemoteItems(version)
        val result = remoteItems.data.map { itemResponseMapper.mapFromEntity(it.value, it.key) }
        local.deleteAllItem()
        insertLocalItem(result, local)
        return result
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

    private fun insertLocalChampion(champions: List<Champion>, local: DDragonLocalDataSource) {
        val championEntities = champions.map { championEntityMapper.mapToEntity(it) }
        championEntities.forEach { local.insertChampion(it) }
    }

    private fun insertLocalSpell(spells: List<Spell>, local: DDragonLocalDataSource) {
        val spellEntities = spells.map { spellEntityMapper.mapToEntity(it) }
        spellEntities.forEach { local.insertSpell(it) }
    }

    private fun insertLocalRune(rune: List<Rune>, local: DDragonLocalDataSource) {
        val runeEntities = rune.map { runeEntityMapper.mapToEntity(it) }
        runeEntities.forEach { local.insertRune(it) }
    }

    private fun insertLocalItem(rune: List<Item>, local: DDragonLocalDataSource) {
        val itemEntities = rune.map { itemEntityMapper.mapToEntity(it) }
        itemEntities.forEach { local.insertItem(it) }
    }
}
