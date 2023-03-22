package gg.op.lol.data.source

import androidx.paging.PagingData
import gg.op.lol.data.models.SummonerEntity
import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.data.models.SummonerInfoEntity
import gg.op.lol.data.repository.SummonerDataSource
import gg.op.lol.data.repository.SummonerLocal
import gg.op.lol.domain.models.MatchHistory
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SummonerLocalDataSource @Inject constructor(
    private val summonerLocal: SummonerLocal
) : SummonerDataSource {

    override fun getSummoners(): List<SummonerEntity> {
        return summonerLocal.getSummoners()
    }

    override suspend fun getSummonerHistory(id: String): SummonerHistoryEntity {
        throw UnsupportedOperationException(
            "getSummonerByNickName is not supported for LocalDataSource."
        )
    }

    override suspend fun getSummonerInfo(nickName: String): SummonerInfoEntity {
        throw UnsupportedOperationException(
            "getSummonerInfo is not supported for LocalDataSource."
        )
    }

    override fun getMatchHistory(puuid: String): Flow<PagingData<MatchHistory>> {
        throw UnsupportedOperationException(
            "getMatchHistory is not supported for LocalDataSource."
        )
    }
}
