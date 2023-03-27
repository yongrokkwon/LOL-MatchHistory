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
    override suspend fun getRemoteChampions(): Flow<List<Champion?>> = flow {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val localChampions = local.getLocalChampions()
        if (localChampions.isEmpty()) {
            val remoteChampions = remote.getRemoteChampions()
            val result = remoteChampions.data.map {
                championResponseMapper.mapFromEntity(it.value)
            }
            insertLocalChampion(result, local)
            emit(result)
        } else {
            val result = localChampions.map { championEntityMapper.mapFromEntity(it) }
            emit(result)
        }
    }

    override suspend fun getRemoteSpells(): Flow<List<Spell?>> = flow {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val localChampions = local.getLocalSpells()
        if (localChampions.isEmpty()) {
            val remoteChampions = remote.getRemoteSpells()
            val result = remoteChampions.data.map {
                spellResponseMapper.mapFromEntity(it.value)
            }
            insertLocalSpell(result, local)
            emit(result)
        } else {
            val result = localChampions.map { spellEntityMapper.mapFromEntity(it) }
            emit(result)
        }
    }

    override suspend fun getRemoteRunes(): Flow<List<Rune?>> = flow {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val localRunes = local.getLocalRunes()
        if (localRunes.isEmpty()) {
            val remoteRunes = remote.getRemoteRunes()
            val result = remoteRunes.map { runeResponseMapper.mapFromEntity(it) }
            insertLocalRune(result, local)
            emit(result)
        } else {
            val result = localRunes.map { runeEntityMapper.mapFromEntity(it) }
            emit(result)
        }
    }

    override suspend fun getRemoteItems(): Flow<List<Item?>> = flow {
        val local = ddragonDataSourceFactory.getLocalDataSource()
        val remote = ddragonDataSourceFactory.getRemoteDataSource()
        val localItems = local.getLocalItems()
        if (localItems.isEmpty()) {
            val remoteItems = remote.getRemoteItems()
            val result = remoteItems.data.map { itemResponseMapper.mapFromEntity(it.value, it.key) }
            insertLocalItem(result, local)
            emit(result)
        } else {
            val result = localItems.map { itemEntityMapper.mapFromEntity(it) }
            emit(result)
        }
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
