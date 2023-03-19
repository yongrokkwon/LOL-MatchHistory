package gg.lol.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gg.lol.android.BuildConfig
import gg.op.lol.data.remote.SummonerRemoteImp
import gg.op.lol.data.remote.api.ServiceFactory
import gg.op.lol.data.remote.api.UserService
import gg.op.lol.data.repository.SummonerRemote
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
    fun provideUserService(): UserService {
        return ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL)
    }
}
