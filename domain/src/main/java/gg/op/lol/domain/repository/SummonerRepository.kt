package gg.op.lol.domain.repository

import gg.op.lol.domain.models.Summoner
import kotlinx.coroutines.flow.Flow

interface SummonerRepository {
    // Remote
    suspend fun getRemoteSummoner(nickName: String): Flow<Summoner>

    // Local
    suspend fun getLocalSummonerByNickName(nickName: String): Flow<Summoner>
    suspend fun getLocalSummoners(): Flow<List<Summoner>>
}
