package gg.op.lol.data

import gg.op.lol.data.mapper.SummonerEntityMapper
import gg.op.lol.data.mapper.SummonerHistoryMapper
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
    private val summonerHistoryMapper: SummonerHistoryMapper
) : SummonerRepository {
    override suspend fun getLocalSummonerByNickName(nickName: String): Flow<Summoner> = flow {
        val summoner = summonerHistoryMapper.mapFromEntity(
            summonerDataSourceFactory.getLocalDataSource().getSummonerHistory(nickName)
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
        val remoteDataSource = summonerDataSourceFactory.getRemoteDataSource()
        val summonerInfo = remoteDataSource.getSummonerInfo(nickName)
        val summonerHistory = remoteDataSource.getSummonerHistory(summonerInfo.id)
        val summoner = summonerHistoryMapper.mapFromEntity(summonerHistory).apply {
            summonerLevel = summonerInfo.summonerLevel
        }
        emit(summoner)
    }
}
