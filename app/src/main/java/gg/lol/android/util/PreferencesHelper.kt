package gg.lol.android.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

open class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        const val PREF_PACKAGE_NAME = "gg.lol.android.preferences"
        private const val PREF_KEY_LOL_API_VERSION = "PREF_KEY_LOL_API_VERSION"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var lolApiVersion: String
        get() = preferences.getString(PREF_KEY_LOL_API_VERSION, null) ?: ""
        set(value) = preferences.edit().putString(PREF_KEY_LOL_API_VERSION, value).apply()
}
