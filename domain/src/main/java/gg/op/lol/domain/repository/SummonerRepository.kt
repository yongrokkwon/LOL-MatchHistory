package gg.op.lol.domain.repository

import androidx.paging.PagingData
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.models.SwapSummoner
import kotlinx.coroutines.flow.Flow

interface SummonerRepository {
    // Remote
    suspend fun getRemoteSummoner(nickName: String): Flow<Summoner>
    fun getRemoteSummonerMatchHistory(puuid: String): Flow<PagingData<MatchHistory>>

    // Local
    suspend fun getLocalSummonerByNickName(nickName: String): Flow<Summoner>
    fun updateFavoriteSummoner(summoner: Summoner)
    fun swapFavoriteSummoner(swapSummoner: SwapSummoner)
    suspend fun getFavoriteSummoner(summonerName: String): Summoner?
}
