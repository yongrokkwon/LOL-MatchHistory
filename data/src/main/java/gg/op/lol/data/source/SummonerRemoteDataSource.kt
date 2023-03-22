package gg.op.lol.data.source

import androidx.paging.PagingData
import gg.op.lol.data.models.SummonerEntity
import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.data.models.SummonerInfoEntity
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

    override suspend fun getSummonerHistory(id: String): SummonerHistoryEntity {
        return summonerRemote.getSummonerHistory(id)
    }

    override suspend fun getSummonerInfo(nickName: String): SummonerInfoEntity {
        return summonerRemote.getSummonerInfo(nickName)
    }

    override fun getMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        return summonerRemote.getMatchHistory(puuid)
    }
}
