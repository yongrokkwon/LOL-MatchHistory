package gg.lol.android

import androidx.compose.ui.graphics.vector.ImageVector
import gg.lol.android.iconpack.Champion
import gg.lol.android.iconpack.Community
import gg.lol.android.iconpack.Esports
import gg.lol.android.iconpack.Home
import gg.lol.android.iconpack.Setting
import kotlin.collections.List

public object IconPack

private var __Icons: List<ImageVector>? = null

public val IconPack.Icons: List<ImageVector>
    get() {
        if (__Icons != null) {
            return __Icons!!
        }
        __Icons = listOf(Champion, Community, Esports, Home, Setting)
        return __Icons!!
    }
