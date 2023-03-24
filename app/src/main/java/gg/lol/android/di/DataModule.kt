package gg.lol.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gg.op.lol.data.DDragonRepositoryImp
import gg.op.lol.data.SummonerRepositoryImp
import gg.op.lol.domain.repository.DDragonRepository
import gg.op.lol.domain.repository.SummonerRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideSummonerRepository(summonerRepository: SummonerRepositoryImp): SummonerRepository =
        summonerRepository

    @Provides
    @Singleton
    fun provideDDragonRepository(ddragonRepository: DDragonRepositoryImp): DDragonRepository =
        ddragonRepository
}
