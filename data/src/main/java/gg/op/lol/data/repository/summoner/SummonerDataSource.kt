package gg.op.lol.data.repository.summoner

import androidx.paging.PagingData
import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.remote.models.SummonerInfoResponse
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.SwapSummoner
import kotlinx.coroutines.flow.Flow

interface SummonerDataSource {
    // Local
    fun getSummoners(): List<SummonerEntity>
    fun getSummoner(summonerName: String): SummonerEntity?
    fun updateFavoriteSummoner(summoner: SummonerEntity)
    fun swapFavoriteSummoner(swapSummoner: SwapSummoner)

    // Remote
    suspend fun getSummonerHistory(id: String): List<SummonerHistoryResponse>
    suspend fun getSummonerInfo(summonerName: String): SummonerInfoResponse
    fun getPagingMatchHistory(puuid: String): Flow<PagingData<MatchHistory>>
    suspend fun getMatchHistory(puuid: String): List<MatchHistory>
}
