package gg.lol.android.util

import android.content.Context
import android.content.SharedPreferences
import gg.lol.android.util.PreferencesHelper.Companion.PREF_KEY_LOL_API_VERSION
import javax.inject.Inject

open class PreferencesHelperImp @Inject constructor(context: Context) : PreferencesHelper {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PreferencesHelper.PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    override var lolApiVersion: String
        get() = preferences.getString(PREF_KEY_LOL_API_VERSION, null) ?: ""
        set(value) = preferences.edit().putString(PREF_KEY_LOL_API_VERSION, value).apply()
}
