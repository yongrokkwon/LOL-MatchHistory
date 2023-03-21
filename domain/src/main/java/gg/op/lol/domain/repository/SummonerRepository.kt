package gg.op.lol.domain.repository

import gg.op.lol.domain.models.SummonerHistory
import kotlinx.coroutines.flow.Flow

interface SummonerRepository {
    // Remote
    suspend fun getRemoteSummoner(nickName: String): Flow<SummonerHistory>

    // Local
    suspend fun getLocalSummonerByNickName(nickName: String): Flow<SummonerHistory>
    suspend fun getLocalSummoners(): Flow<List<SummonerHistory>>
}
