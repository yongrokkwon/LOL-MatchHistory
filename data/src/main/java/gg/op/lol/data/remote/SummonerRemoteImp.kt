package gg.op.lol.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import gg.op.lol.data.remote.api.MatchService
import gg.op.lol.data.remote.api.SummonerService
import gg.op.lol.data.remote.mapper.MatchHistoryMapper
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.remote.models.SummonerInfoResponse
import gg.op.lol.data.repository.summoner.SummonerRemote
import gg.op.lol.domain.models.MatchHistory
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SummonerRemoteImp @Inject constructor(
    private val summonerService: SummonerService,
    private val matchService: MatchService,
    private val matchHistoryMapper: MatchHistoryMapper
) : SummonerRemote {

    override suspend fun getSummonerHistory(id: String): List<SummonerHistoryResponse> {
        val summonerHistory = summonerService.getSummonerHistory(id)
        return updateUnRank(summonerHistory)
    }

    override suspend fun getSummonerInfo(nickName: String): SummonerInfoResponse {
        return summonerService.getSummonerInfo(nickName)
    }

    override fun getMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_COUNT),
            pagingSourceFactory = {
                MatchHistoryPagingSource(
                    matchService,
                    matchHistoryMapper,
                    puuid
                )
            }
        ).flow
    }

    private fun updateUnRank(list: List<SummonerHistoryResponse>): List<SummonerHistoryResponse> {
        val result = ArrayList<SummonerHistoryResponse>()
        for (i in 0 until 2) {
            result.add(
                list.getOrElse(i) {
                    SummonerHistoryResponse(
                        freshBlood = false,
                        hotStreak = false,
                        inactive = false,
                        leagueId = "",
                        leaguePoints = 0,
                        losses = 0,
                        queueType = "RANKED_FLEX_SR",
                        rank = "",
                        summonerId = "",
                        summonerName = "",
                        tier = "Unranked",
                        veteran = false,
                        wins = 0
                    )
                }
            )
        }
        return result
    }
}
