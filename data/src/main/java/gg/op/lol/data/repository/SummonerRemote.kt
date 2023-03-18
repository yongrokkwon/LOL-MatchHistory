package gg.op.lol.data.repository

import gg.op.lol.data.models.SummonerModel

interface SummonerRemote {
    suspend fun getSummonerByNickName(nickName: String): SummonerModel
}
