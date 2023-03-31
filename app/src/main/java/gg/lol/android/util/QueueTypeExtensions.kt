package gg.lol.android.util

import androidx.annotation.StringRes
import gg.lol.android.R
import gg.op.lol.domain.models.QueueType

object QueueTypeExtensions {
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
}
