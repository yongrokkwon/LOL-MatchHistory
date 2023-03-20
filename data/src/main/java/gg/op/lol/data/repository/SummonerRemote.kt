package gg.op.lol.data.repository

import gg.op.lol.data.models.SummonerHistoryModel
import gg.op.lol.data.models.SummonerInfoModel

interface SummonerRemote {
    suspend fun getSummonerHistory(id: String): SummonerHistoryModel
    suspend fun getSummonerInfo(nickName: String): SummonerInfoModel
}
