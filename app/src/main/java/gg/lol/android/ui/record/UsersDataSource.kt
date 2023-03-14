package gg.lol.android.ui.record

import gg.lol.android.network.Result
import gg.lol.android.network.response.SummonerResponse

interface UsersDataSource {
    suspend fun getSummoner(name: String): Result<SummonerResponse>
}