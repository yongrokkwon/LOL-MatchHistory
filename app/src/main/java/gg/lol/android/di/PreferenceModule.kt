package gg.lol.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gg.lol.android.util.PreferencesHelper
import gg.lol.android.util.PreferencesHelperImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun providePresentationPreferenceHelper(
        @ApplicationContext context: Context
    ): PreferencesHelper {
        return PreferencesHelperImp(context)
    }
}
