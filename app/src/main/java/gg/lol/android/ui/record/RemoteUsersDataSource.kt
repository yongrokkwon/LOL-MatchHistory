package gg.lol.android.ui.record

import gg.lol.android.network.NetworkException
import gg.lol.android.network.Result
import gg.lol.android.network.UserService
import gg.lol.android.network.response.SummonerResponse

class RemoteUsersDataSource(private val userService: UserService) : UsersDataSource {
    override suspend fun getSummoner(name: String): Result<SummonerResponse> = try {
        val response = userService.getSummoner(name)
        if (response.isSuccessful) {
            response.body()?.let { Result.Success(it) }
                ?: Result.Error(NetworkException(response.code(), response.message()))
        } else {
            Result.Error(NetworkException(response.code(), response.message()))
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}