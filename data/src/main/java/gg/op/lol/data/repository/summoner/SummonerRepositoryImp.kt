package gg.op.lol.data.repository.summoner

import androidx.paging.PagingData
import gg.op.lol.data.mapper.SummonerEntityMapper
import gg.op.lol.data.mapper.SummonerHistoryResponseMapper
import gg.op.lol.data.source.ddragon.DDragonDataSourceFactory
import gg.op.lol.data.source.summoner.SummonerDataSourceFactory
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.MySummoner
import gg.op.lol.domain.models.QueueType
import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.models.SwapSummoner
import gg.op.lol.domain.models.Tier
import gg.op.lol.domain.repository.SummonerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SummonerRepositoryImp @Inject constructor(
    private val ddragonDataSourceFactory: DDragonDataSourceFactory,
    private val summonerDataSourceFactory: SummonerDataSourceFactory,
    private val summonerEntityMapper: SummonerEntityMapper,
    private val summonerHistoryEntityMapper: SummonerHistoryResponseMapper
) : SummonerRepository {

    override suspend fun getLocalSummonerByNickName(nickName: String): Flow<Summoner> {
        throw UnsupportedOperationException(
            "getLocalSummonerByNickName TODO"
        )
    }

    override suspend fun getFavoriteSummoner(summonerName: String): Summoner? {
        return summonerDataSourceFactory.getLocalDataSource().getSummoner(summonerName)
            ?.let { summonerEntityMapper.mapFromEntity(it) }
    }

    override fun updateFavoriteSummoner(summoner: Summoner) {
        val entity = summonerEntityMapper.mapToEntity(summoner)
        summonerDataSourceFactory.getLocalDataSource().updateFavoriteSummoner(entity)
    }

    override fun swapFavoriteSummoner(swapSummoner: SwapSummoner) {
        summonerDataSourceFactory.getLocalDataSource().swapFavoriteSummoner(swapSummoner)
    }

    override suspend fun getRemoteSummoner(summonerName: String): Flow<Summoner> = flow {
        val remoteDataSource = summonerDataSourceFactory.getRemoteDataSource()
        val summonerInfo = remoteDataSource.getSummonerInfo(summonerName)
        val summonerBodies = remoteDataSource.getSummonerHistory(summonerInfo.id)
            .map { summonerHistoryEntityMapper.mapFromEntity(it) }
            .sortedBy { it.queueType.queueId }
        val summoner = Summoner(
            summonerLevel = summonerInfo.summonerLevel,
            summonerName = summonerInfo.name,
            puuid = summonerInfo.puuid,
            profileIconId = summonerInfo.profileIconId,
            histories = summonerBodies
        )
        emit(summoner)
    }

    override fun getRemotePagingMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        return summonerDataSourceFactory.getRemoteDataSource().getPagingMatchHistory(puuid)
    }

    override suspend fun getMySummoner(summonerName: String): Flow<MySummoner> = flow {
        getRemoteSummoner(summonerName).collect { summoner ->
            val tierHistory = summoner.histories.find { it.queueType == QueueType.RANKED_SOLO_5X5 }
            val tier = tierHistory?.let { Tier.valueOf(it.tier, it.rank) } ?: Tier.UNRANK
            val matchHistories = summonerDataSourceFactory.getRemoteDataSource()
                .getMatchHistory(summoner.puuid)
            val participants = matchHistories.flatMap {
                it.info.participants.filter { matchHistory ->
                    matchHistory.puuid == summoner.puuid
                }
            }
            val matchHistoryMap = participants.groupBy { it.championId }
                .toList()
                .sortedByDescending { it.second.size }
                .toMap()
            val wins = participants.filter { it.win }.size
            val losses = participants.filterNot { it.win }.size
            val kills = participants.sumOf { it.kills }
            val assists = participants.sumOf { it.assists }
            val deaths = participants.sumOf { it.deaths }

            val championEntities = ddragonDataSourceFactory.getLocalDataSource().getLocalChampions()

            val championMap = matchHistoryMap.flatMap { (id, participants) ->
                participants.map { participant ->
                    val championKills = participants.sumOf { participant.kills }
                    val championDeaths = participants.sumOf { participant.deaths }
                    val championAssists = participants.sumOf { participant.assists }
                    val championWins = participants.filter { participant.win }.size
                    val championLosses = participants.filterNot { participant.win }.size
                    val championEntity = championEntities.find { it.id == participant.championId }
                    val champion = MySummoner.Champion(
                        championEntity?.imagePath ?: "",
                        calculateWinRate(championWins, championLosses),
                        calculateKda(championKills, championDeaths, championAssists)
                    )
                    id to champion
                }
            }.toMap()
            val champions = championMap.toList().chunked(3).getOrNull(0) ?: emptyList()
            val result = MySummoner(
                summonerName = summonerName,
                summonerLevel = summoner.summonerLevel,
                profileIconId = summoner.profileIconId,
                tier = tier,
                leaguePoints = tierHistory?.leaguePoints ?: 0,
                wins = wins,
                losses = losses,
                winRate = calculateWinRate(wins, losses),
                kda = calculateKda(kills, deaths, assists),
                champion = champions
            )
            emit(result)
        }
    }
}

fun calculateWinRate(wins: Int, losses: Int): Int {
    val totalGames = wins + losses
    if (totalGames == 0) {
        return 0
    }
    return (wins.toDouble() / totalGames.toDouble() * 100).toInt()
}

fun calculateKda(kills: Int, deaths: Int, assists: Int): Double {
    val kda = (kills + assists) / deaths.toDouble()
    return if (kda.isNaN() || kda.isInfinite()) 0.0 else String.format("%.2f", kda).toDouble()
}
