package gg.lol.android

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.FlipperLeakEventListener
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.data.remote.api.ServiceFactory
import leakcanary.LeakCanary

@HiltAndroidApp
class LOLMatchHistoryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LeakCanary.config = LeakCanary.config.run {
            copy(eventListeners = eventListeners + FlipperLeakEventListener())
        }
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(
                SharedPreferencesFlipperPlugin(
                    this,
                    PreferencesHelper.PREF_PACKAGE_NAME
                )
            )
            client.addPlugin(ServiceFactory.FLIPPER_NETWORK_PLUGIN)
            client.addPlugin(LeakCanary2FlipperPlugin())
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.start()
        }
    }
}
