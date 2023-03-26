package gg.op.lol.data

import androidx.paging.PagingData
import gg.op.lol.data.mapper.SummonerEntityMapper
import gg.op.lol.data.mapper.SummonerHistoryEntityMapper
import gg.op.lol.data.source.SummonerDataSourceFactory
import gg.op.lol.domain.models.MatchHistory
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
    private val summonerHistoryEntityMapper: SummonerHistoryEntityMapper
) : SummonerRepository {
    override suspend fun getLocalSummonerByNickName(nickName: String): Flow<Summoner> =
        flow {
            val summoner = summonerHistoryEntityMapper.mapFromEntity(
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
        remoteDataSource.getMatchHistory(summonerInfo.puuid)
        val summoner = summonerHistoryEntityMapper.mapFromEntity(summonerHistory).apply {
            summonerLevel = summonerInfo.summonerLevel
            summonerName = summonerInfo.name
            puuid = summonerInfo.puuid
        }
        emit(summoner)
    }

//    override fun getRemoteSummonerMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
//        return flow {
//            summonerDataSourceFactory.getRemoteDataSource().getMatchHistory(puuid)
//                .collect { matchHistoryEntity ->
//                    val matchHistory =
//                        matchHistoryEntity.map { matchHistoryEntityMapper.mapFromEntity(it) }
//                    emit(matchHistory)
//                }
//        }
//    }

    override fun getRemoteSummonerMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        return summonerDataSourceFactory.getRemoteDataSource().getMatchHistory(puuid)
    }
}
