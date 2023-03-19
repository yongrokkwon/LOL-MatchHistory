package gg.op.lol.data

import gg.op.lol.data.mapper.SummonerEntityMapper
import gg.op.lol.data.mapper.SummonerResponseMapper
import gg.op.lol.data.source.SummonerDataSourceFactory
import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.repository.SummonerRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class SummonerRepositoryImp @Inject constructor(
    private val summonerDataSourceFactory: SummonerDataSourceFactory,
    private val summonerEntityMapper: SummonerEntityMapper,
    private val summonerResponseMapper: SummonerResponseMapper
) : SummonerRepository {
    override suspend fun getLocalSummonerByNickName(nickName: String): Flow<Summoner> = flow {
        val summoner = summonerResponseMapper.mapFromEntity(
            summonerDataSourceFactory.getLocalDataSource().getSummoner(nickName)
        )
        emit(summoner)
    }

    override suspend fun getLocalSummoners(): Flow<List<Summoner>> = flow {
        val summoners = summonerDataSourceFactory.getLocalDataSource().getSummoners().map {
            summonerEntityMapper.mapFromEntity(it)
        }
//        emit(summoners) TODO
    }

    override suspend fun getRemoteSummoner(nickName: String): Flow<Summoner> = flow {
        val summoner = summonerResponseMapper.mapFromEntity(
            summonerDataSourceFactory.getRemoteDataSource().getSummoner(nickName)
        )
        emit(summoner)
    }
}
