package gg.lol.android.network

import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): Response<String> // TODO
}