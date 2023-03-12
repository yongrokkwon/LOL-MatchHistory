package gg.lol.android.repository

import gg.lol.android.data.summoner.Summoner
import gg.lol.android.data.summoner.SummonerDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SummonerRepository @Inject constructor(
    private val summonerDao: SummonerDao
) {

    fun getSummonerByNickName(nickName: String) = summonerDao.getSummonerByNickName(nickName)
    fun fetchSummoner() = summonerDao.fetchSummoners()
    suspend fun insertSummoner(summoner: Summoner) = summonerDao.insert(summoner)

}