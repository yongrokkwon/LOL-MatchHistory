package gg.op.lol.data.source.summoner

import androidx.paging.PagingData
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.remote.models.SummonerInfoResponse
import gg.op.lol.data.repository.summoner.SummonerDataSource
import gg.op.lol.data.repository.summoner.SummonerRemote
import gg.op.lol.domain.models.MatchHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SummonerRemoteDataSource @Inject constructor(
    private val summonerRemote: SummonerRemote
) : SummonerDataSource {
    suspend fun getSummonerHistory(id: String): List<SummonerHistoryResponse> {
        return summonerRemote.getSummonerHistory(id)
    }

    suspend fun getSummonerInfo(summonerName: String): SummonerInfoResponse {
        return summonerRemote.getSummonerInfo(summonerName)
    }

    fun getPagingMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        return summonerRemote.getPagingMatchHistory(puuid)
    }

    suspend fun getMatchHistory(puuid: String): List<MatchHistory> {
        return summonerRemote.getMatchHistory(puuid)
    }
}
