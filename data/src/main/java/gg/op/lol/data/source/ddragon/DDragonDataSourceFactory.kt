package gg.op.lol.data.source.ddragon

import javax.inject.Inject

open class DDragonDataSourceFactory @Inject constructor(
    private val localDataSource: DDragonLocalDataSource,
    private val remoteDataSource: DDragonRemoteDataSource
) {

    fun getRemoteDataSource(): DDragonRemoteDataSource {
        return remoteDataSource
    }

    fun getLocalDataSource(): DDragonLocalDataSource {
        return localDataSource
    }
}
