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
import gg.op.lol.domain.models.QueueType
import gg.op.lol.domain.models.Tier
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
        val element = SummonerHistoryResponse(
            freshBlood = false,
            hotStreak = false,
            inactive = false,
            leagueId = "",
            leaguePoints = 0,
            losses = 0,
            queueType = "",
            rank = "",
            summonerId = "",
            summonerName = "",
            tier = Tier.UNRANK.javaClass.simpleName,
            veteran = false,
            wins = 0
        )
        when (list.size) {
            0 -> {
                result.add(element.copy(queueType = QueueType.RANKED_SOLO_5X5.name))
                result.add(element.copy(queueType = QueueType.RANKED_FLEX_SR.name))
            }
            1 -> {
                val type = list.first()
                val otherType = if (type.queueType == QueueType.RANKED_FLEX_SR.name) {
                    QueueType.RANKED_SOLO_5X5
                } else {
                    QueueType.RANKED_FLEX_SR
                }
                result.add(type)
                result.add(element.copy(queueType = otherType.name))
            }
            2 -> {
                if (list[0] != list[1]) {
                    result.addAll(list)
                }
            }
        }
        return result
    }
}
