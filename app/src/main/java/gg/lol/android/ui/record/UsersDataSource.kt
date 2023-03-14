package gg.lol.android.ui.record

import gg.lol.android.network.Result

interface UsersDataSource {
    suspend fun fetchUser(): Result<String>
}