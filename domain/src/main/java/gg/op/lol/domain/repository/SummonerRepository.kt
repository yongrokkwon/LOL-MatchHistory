package gg.op.lol.domain.repository

import androidx.paging.PagingData
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.Summoner
import kotlinx.coroutines.flow.Flow

interface SummonerRepository {
    // Remote
    suspend fun getRemoteSummoner(nickName: String): Flow<Summoner>
    fun getRemoteSummonerMatchHistory(puuid: String): Flow<PagingData<MatchHistory>>

    // Local
    suspend fun getLocalSummonerByNickName(nickName: String): Flow<Summoner>
    suspend fun getLocalSummoners(): Flow<List<Summoner>>
}
