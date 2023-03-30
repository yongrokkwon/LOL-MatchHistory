package gg.lol.android.util

import androidx.annotation.DrawableRes
import gg.lol.android.R
import gg.op.lol.domain.models.Tier

object TierExtensions {
    @DrawableRes
    fun Tier.toDrawable(): Int {
        return when (this) {
            is Tier.IRON -> R.drawable.iron
            is Tier.BRONZE -> R.drawable.bronze
            is Tier.SILVER -> R.drawable.silver
            is Tier.GOLD -> R.drawable.gold
            is Tier.PLATINUM -> R.drawable.platinum
            is Tier.DIAMOND -> R.drawable.diamond
            is Tier.MASTER -> R.drawable.master
            is Tier.GRANDMASTER -> R.drawable.grandmaster
            is Tier.CHALLENGER -> R.drawable.challenger
            else -> R.drawable.unranked
        }
    }
}
