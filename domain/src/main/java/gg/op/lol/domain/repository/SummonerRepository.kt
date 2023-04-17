package gg.op.lol.domain.repository

import androidx.paging.PagingData
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.MySummoner
import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.models.SwapSummoner
import kotlinx.coroutines.flow.Flow

interface SummonerRepository {
    // Remote
    suspend fun getRemoteSummoner(summonerName: String): Flow<Summoner>
    fun getRemotePagingMatchHistory(puuid: String): Flow<PagingData<MatchHistory>>
    suspend fun getMySummoner(summonerName: String): Flow<MySummoner>

    // Local
    fun updateFavoriteSummoner(summoner: Summoner)
    fun swapFavoriteSummoner(swapSummoner: SwapSummoner)
    suspend fun getFavoriteSummoner(summonerName: String): Summoner?
}
