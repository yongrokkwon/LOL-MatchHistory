package gg.lol.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gg.op.lol.data.local.SummonerLocalImp
import gg.op.lol.data.repository.SummonerLocal
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideSummonerLocal(summonerLocal: SummonerLocalImp): SummonerLocal {
        return summonerLocal
    }
}
