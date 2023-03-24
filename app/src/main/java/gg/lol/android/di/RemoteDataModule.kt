package gg.lol.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gg.lol.android.BuildConfig
import gg.op.lol.data.remote.DDragonRemoteImp
import gg.op.lol.data.remote.SummonerRemoteImp
import gg.op.lol.data.remote.api.DDragonService
import gg.op.lol.data.remote.api.MatchService
import gg.op.lol.data.remote.api.ServiceFactory
import gg.op.lol.data.remote.api.SummonerService
import gg.op.lol.data.repository.SummonerRemote
import gg.op.lol.data.repository.ddragon.DDragonRemote
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    @Singleton
    fun provideSummonerRemote(summonerRemoteImp: SummonerRemoteImp): SummonerRemote {
        return summonerRemoteImp
    }

    @Provides
    @Singleton
    fun provideDDragonRemote(ddragonRemoteImp: DDragonRemoteImp): DDragonRemote {
        return ddragonRemoteImp
    }

    @Provides
    @Singleton
    fun provideSummonerService(): SummonerService {
        return ServiceFactory.createRetrofitService(BuildConfig.DEBUG, BuildConfig.KR_URL)
    }

    @Provides
    @Singleton
    fun provideMatchService(): MatchService {
        return ServiceFactory.createRetrofitService(BuildConfig.DEBUG, BuildConfig.ASIA_URL)
    }

    @Provides
    @Singleton
    fun provideDDragonService(): DDragonService {
        return ServiceFactory.createRetrofitService(BuildConfig.DEBUG, BuildConfig.DDRAGON_URL)
    }
}
