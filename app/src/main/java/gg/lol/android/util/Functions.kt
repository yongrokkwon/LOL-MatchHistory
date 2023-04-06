package gg.lol.android.util

import androidx.lifecycle.SavedStateHandle

object Functions {

    fun <T> getArg(savedStateHandle: SavedStateHandle, argument: String): T? {
        return savedStateHandle.get<T>(argument)
    }
}
