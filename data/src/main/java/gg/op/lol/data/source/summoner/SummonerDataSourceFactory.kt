package gg.op.lol.data.source.summoner

import javax.inject.Inject

open class SummonerDataSourceFactory @Inject constructor(
    private val localDataSource: SummonerLocalDataSource,
    private val remoteDataSource: SummonerRemoteDataSource
) {

    fun getRemoteDataSource(): SummonerRemoteDataSource {
        return remoteDataSource
    }

    fun getLocalDataSource(): SummonerLocalDataSource {
        return localDataSource
    }
}
