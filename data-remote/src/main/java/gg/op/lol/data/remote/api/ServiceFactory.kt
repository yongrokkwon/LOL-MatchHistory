package gg.op.lol.data.remote.api

import gg.op.lol.remote.BuildConfig
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {

    fun create(isDebug: Boolean, baseUrl: String): UserService {
        val retrofit = createRetrofit(isDebug, baseUrl)
        return retrofit.create(UserService::class.java)
    }

    private fun createRetrofit(isDebug: Boolean, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient(createLoggingInterceptor(isDebug), createHeaderInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun createHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val requestWithHeaders: Request = originalRequest.newBuilder()
                .addHeader("X-Riot-Token", BuildConfig.API_KEY)
//                .addHeader("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
//                .addHeader("Origin", "https://developer.riotgames.com")
                .build()
            chain.proceed(requestWithHeaders)
        }
    }
    // "Accept-Charset": "application/x-www-form-urlencoded; charset=UTF-8",
    //    "Origin": "https://developer.riotgames.com",

    private const val OK_HTTP_TIMEOUT = 60L
}
