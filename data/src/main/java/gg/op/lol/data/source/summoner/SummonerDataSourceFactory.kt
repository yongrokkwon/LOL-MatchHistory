package gg.op.lol.data.source.summoner

import gg.op.lol.data.repository.summoner.SummonerDataSource
import javax.inject.Inject

open class SummonerDataSourceFactory @Inject constructor(
    private val localDataSource: SummonerLocalDataSource,
    private val remoteDataSource: SummonerRemoteDataSource
) {

    fun getRemoteDataSource(): SummonerDataSource {
        return remoteDataSource
    }

    fun getLocalDataSource(): SummonerDataSource {
        return localDataSource
    }
}