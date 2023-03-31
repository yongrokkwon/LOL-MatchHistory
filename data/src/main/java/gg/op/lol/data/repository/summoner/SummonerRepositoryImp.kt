package gg.op.lol.data.repository.summoner

import androidx.paging.PagingData
import gg.op.lol.data.mapper.SearchHistoryEntityMapper
import gg.op.lol.data.mapper.SummonerHistoryResponseMapper
import gg.op.lol.data.source.summoner.SummonerDataSourceFactory
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.repository.SummonerRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// TODO
@Singleton
class SummonerRepositoryImp @Inject constructor(
    private val summonerDataSourceFactory: SummonerDataSourceFactory,
    private val searchHistoryEntityMapper: SearchHistoryEntityMapper,
    private val summonerHistoryEntityMapper: SummonerHistoryResponseMapper
) : SummonerRepository {
    // TODO
    override suspend fun getLocalSummonerByNickName(nickName: String): Flow<Summoner> {
        throw UnsupportedOperationException(
            "getLocalSummonerByNickName TODO"
        )
    }

    override suspend fun getRemoteSummoner(nickName: String): Flow<Summoner> = flow {
        println("## 1")
        val remoteDataSource = summonerDataSourceFactory.getRemoteDataSource()
        val summonerInfo = remoteDataSource.getSummonerInfo(nickName)
        println("## 2")
        val summonerHistory = remoteDataSource.getSummonerHistory(summonerInfo.id)
        println("## 3")
        summonerHistory.map { summonerHistoryEntityMapper.mapFromEntity(it) }
            .sortedBy { it.queueType }
        println("## 4")
        val summoner = Summoner(
            summonerLevel = summonerInfo.summonerLevel,
            summonerName = summonerInfo.name,
            puuid = summonerInfo.puuid,
            profileIconId = summonerInfo.profileIconId,
            histories = summonerHistory.map { summonerHistoryEntityMapper.mapFromEntity(it) }
        )
        println("## 5")
        emit(summoner)
    }

    override fun getRemoteSummonerMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        return summonerDataSourceFactory.getRemoteDataSource().getMatchHistory(puuid)
    }
}
