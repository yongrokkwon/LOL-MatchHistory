package gg.op.lol.data.source

import androidx.paging.PagingData
import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.data.remote.models.SummonerInfoResponse
import gg.op.lol.data.repository.SummonerDataSource
import gg.op.lol.data.repository.SummonerRemote
import gg.op.lol.domain.models.MatchHistory
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SummonerRemoteDataSource @Inject constructor(
    private val summonerRemote: SummonerRemote
) : SummonerDataSource {

    override fun getSummoners(): List<SummonerEntity> {
        throw UnsupportedOperationException("Get Summoners is not supported for RemoteDataSource.")
    }

    override suspend fun getSummonerHistory(id: String): List<SummonerHistoryResponse> {
        return summonerRemote.getSummonerHistory(id)
    }

    override suspend fun getSummonerInfo(nickName: String): SummonerInfoResponse {
        return summonerRemote.getSummonerInfo(nickName)
    }

    override fun getMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        return summonerRemote.getMatchHistory(puuid)
    }
}
