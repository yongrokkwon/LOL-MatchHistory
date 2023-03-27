package gg.op.lol.data.repository.summoner

import androidx.paging.PagingData
import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.remote.models.SummonerInfoResponse
import gg.op.lol.domain.models.MatchHistory
import kotlinx.coroutines.flow.Flow

interface SummonerDataSource {
    // Local
    fun getSummoners(): List<SummonerEntity>

    // Remote
    suspend fun getSummonerHistory(id: String): List<SummonerHistoryResponse>
    suspend fun getSummonerInfo(nickName: String): SummonerInfoResponse
    fun getMatchHistory(puuid: String): Flow<PagingData<MatchHistory>>
}
