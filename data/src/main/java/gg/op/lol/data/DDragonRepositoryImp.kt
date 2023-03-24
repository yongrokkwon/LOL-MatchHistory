package gg.op.lol.data

import gg.op.lol.data.mapper.ChampionEntityMapper
import gg.op.lol.data.mapper.ChampionResponseMapper
import gg.op.lol.data.source.ddragon.DDragonDataSourceFactory
import gg.op.lol.data.source.ddragon.DDragonLocalDataSource
import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class DDragonRepositoryImp @Inject constructor(
    private val ddragonDataSourceFactory: DDragonDataSourceFactory,
    private val championResponseMapper: ChampionResponseMapper,
    private val championEntityMapper: ChampionEntityMapper
) : DDragonRepository {

    override suspend fun getChampions(): Flow<List<Champion?>> = flow {
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

    private fun insertLocalChampion(champions: List<Champion>, local: DDragonLocalDataSource) {
        val championEntities = champions.map { championEntityMapper.mapToEntity(it) }
        championEntities.forEach { local.insertChampion(it) }
    }
}
