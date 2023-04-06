package gg.lol.android.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import gg.lol.android.R
import gg.op.lol.domain.models.QueueType
import gg.op.lol.domain.models.Tier

object Extensions {

    fun Context.showToast(@StringRes resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }

    @StringRes
    fun QueueType.toName(): Int {
        return when (this) {
            QueueType.NORMAL -> R.string.match_normal
            QueueType.RANKED_SOLO_5X5 -> R.string.match_solo_rank
            QueueType.RANKED_FLEX_SR -> R.string.match_flex_rank
            QueueType.ARAM -> R.string.match_flex_rank
            QueueType.CLASH -> R.string.match_clash
            in QueueType.AI_01..QueueType.AI_04 -> R.string.match_ai
            QueueType.URF -> R.string.match_urf
            QueueType.PORO -> R.string.match_poro
            QueueType.OFA -> R.string.match_ofa
            in QueueType.TUTORIAL_01..QueueType.TUTORIAL_03 -> R.string.match_tutorial
            else -> R.string.match_etc
        }
    }

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
