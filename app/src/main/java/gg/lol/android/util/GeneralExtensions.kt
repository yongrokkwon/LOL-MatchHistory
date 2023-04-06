package gg.lol.android.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

object GeneralExtensions {

    fun Context.showToast(@StringRes resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }
}
