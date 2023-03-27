package gg.op.lol.data

import androidx.paging.PagingData
import gg.op.lol.data.mapper.SummonerEntityMapper
import gg.op.lol.data.mapper.SummonerHistoryResponseMapper
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
    private val summonerHistoryEntityMapper: SummonerHistoryResponseMapper
) : SummonerRepository {
    // TODO
    override suspend fun getLocalSummonerByNickName(nickName: String): Flow<Summoner> {
        throw UnsupportedOperationException(
            "getLocalSummonerByNickName TODO"
        )
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
        val summoner = Summoner(
            summonerLevel = summonerInfo.summonerLevel,
            summonerName = summonerInfo.name,
            puuid = summonerInfo.puuid,
            profileIconId = summonerInfo.profileIconId,
            histories = summonerHistory.map { summonerHistoryEntityMapper.mapFromEntity(it) }
        )
        emit(summoner)
    }

    override fun getRemoteSummonerMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        return summonerDataSourceFactory.getRemoteDataSource().getMatchHistory(puuid)
    }
}
