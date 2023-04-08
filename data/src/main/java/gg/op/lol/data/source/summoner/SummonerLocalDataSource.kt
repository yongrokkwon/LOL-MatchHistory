package gg.op.lol.data.source.summoner

import androidx.paging.PagingData
import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.remote.models.SummonerInfoResponse
import gg.op.lol.data.repository.summoner.SummonerDataSource
import gg.op.lol.data.repository.summoner.SummonerLocal
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.SwapSummoner
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SummonerLocalDataSource @Inject constructor(
    private val summonerLocal: SummonerLocal
) : SummonerDataSource {

    override fun getSummoners(): List<SummonerEntity> {
        return summonerLocal.getSummoners()
    }

    override fun getSummoner(summonerName: String): SummonerEntity? {
        return summonerLocal.getSummoner(summonerName)
    }

    override fun updateFavoriteSummoner(summoner: SummonerEntity) {
        summonerLocal.updateSummoner(summoner)
    }

    override fun swapFavoriteSummoner(swapSummoner: SwapSummoner) {
        summonerLocal.swapFavoriteSummoner(swapSummoner)
    }

    override suspend fun getSummonerHistory(id: String): List<SummonerHistoryResponse> {
        throw UnsupportedOperationException(
            "getSummonerByNickName is not supported for LocalDataSource."
        )
    }

    override suspend fun getSummonerInfo(summonerName: String): SummonerInfoResponse {
        throw UnsupportedOperationException(
            "getSummonerInfo is not supported for LocalDataSource."
        )
    }

    override fun getPagingMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        throw UnsupportedOperationException(
            "getPagingMatchHistory is not supported for LocalDataSource."
        )
    }

    override suspend fun getMatchHistory(puuid: String): List<MatchHistory> {
        throw UnsupportedOperationException(
            "getMatchHistory is not supported for LocalDataSource."
        )
    }
}
