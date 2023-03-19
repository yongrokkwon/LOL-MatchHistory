package gg.op.lol.data.repository

import gg.op.lol.data.models.SummonerHistoryModel

interface SummonerRemote {
    suspend fun getSummoner(nickName: String): SummonerHistoryModel
}
