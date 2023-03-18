package gg.lol.android.ui.record

import gg.lol.android.network.Result
import gg.op.lol.data.remote.models.SummonerResponseModel

interface UsersDataSource {
    suspend fun getSummoner(name: String): Result<SummonerResponseModel>
}