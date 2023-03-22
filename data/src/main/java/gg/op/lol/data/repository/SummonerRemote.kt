package gg.op.lol.data.repository

import androidx.paging.PagingData
import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.data.models.SummonerInfoEntity
import gg.op.lol.domain.models.MatchHistory
import kotlinx.coroutines.flow.Flow

interface SummonerRemote {
    suspend fun getSummonerHistory(id: String): SummonerHistoryEntity
    suspend fun getSummonerInfo(nickName: String): SummonerInfoEntity
    fun getMatchHistory(puuid: String): Flow<PagingData<MatchHistory>>
}
