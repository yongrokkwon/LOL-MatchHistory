package gg.lol.android.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

//    @Singleton
//    @Provides
//    fun provideRecordService(): UserService {
//        return Retrofit.Builder()
//            .baseUrl("https://790b-218-51-73-161.jp.ngrok.io/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(UserService::class.java)
//    }
}
