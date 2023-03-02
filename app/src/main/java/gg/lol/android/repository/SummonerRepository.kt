package gg.lol.android.repository

import gg.lol.android.data.summoner.SummonerDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SummonerRepository @Inject constructor(
    private val summonerDao: SummonerDao
) {

//    fun get() = gardenPlantingDao.getPlantedGardens()

}