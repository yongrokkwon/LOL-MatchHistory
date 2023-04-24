package gg.lol.android.util

interface PreferencesHelper {

    companion object {
        const val PREF_PACKAGE_NAME = "gg.lol.android.preferences"
        const val PREF_KEY_LOL_API_VERSION = "PREF_KEY_LOL_API_VERSION"
    }

    var lolApiVersion: String
}
