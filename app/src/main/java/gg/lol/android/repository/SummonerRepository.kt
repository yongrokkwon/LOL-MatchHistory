package gg.lol.android.repository

import gg.lol.android.data.summoner.Summoner
import gg.lol.android.data.summoner.SummonerDao
import gg.lol.android.network.NetworkException
import gg.lol.android.network.Result
import gg.lol.android.network.UserService
import gg.lol.android.network.response.SummonerResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SummonerRepository @Inject constructor(
    private val summonerDao: SummonerDao,
    private val userService: UserService
) {

    fun getSummonerByNickName(nickName: String) = summonerDao.getSummonerByNickName(nickName)
    fun fetchSummoner() = summonerDao.fetchSummoners()
    suspend fun insertSummoner(summoner: Summoner) = summonerDao.insert(summoner)

    suspend fun getSummoner(name: String): Result<SummonerResponse> {
        return try {
            val response = userService.getSummoner(name)
            val body = response.body()
            body?.let { Result.Success(body) } ?: Result.Error(
                NetworkException(
                    response.code(),
                    response.message()
                )
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}