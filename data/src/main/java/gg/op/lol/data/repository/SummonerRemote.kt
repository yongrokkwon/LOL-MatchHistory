package gg.op.lol.data.repository

import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.data.models.SummonerInfoEntity

interface SummonerRemote {
    suspend fun getSummonerHistory(id: String): SummonerHistoryEntity
    suspend fun getSummonerInfo(nickName: String): SummonerInfoEntity
}
