package gg.lol.android.ui.record

import gg.lol.android.network.NetworkException
import gg.lol.android.network.Result
import gg.lol.android.network.UserService

class RemoteUsersDataSource(private val userService: UserService) : UsersDataSource {
    override suspend fun fetchUser(): Result<String> = try {
        val response = userService.getUsers()
        if (response.isSuccessful) {
            val users = response.body() ?: ""
            Result.Success(users)
        } else {
            Result.Error(NetworkException(response.code(), response.message()))
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}