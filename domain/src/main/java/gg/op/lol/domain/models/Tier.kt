package gg.op.lol.domain.models

import gg.op.lol.domain.romanToArabic
import java.util.Locale

sealed class Tier(val rank: String) {
    class CHALLENGER(rank: String) : Tier(rank)
    class GRANDMASTER(rank: String) : Tier(rank)
    class MASTER(rank: String) : Tier(rank)
    class DIAMOND(rank: String) : Tier(rank)
    class PLATINUM(rank: String) : Tier(rank)
    class GOLD(rank: String) : Tier(rank)
    class SILVER(rank: String) : Tier(rank)
    class BRONZE(rank: String) : Tier(rank)
    class IRON(rank: String) : Tier(rank)
    object UNRANK : Tier("")

    fun toName(): String {
        return getTierAsString().capitalizeFirstLetterAndLowercaseRest() + " " +
            rank.romanToArabic()
    }

    fun toSummaryName(): String {
        return when (this) {
            is MASTER -> "M1"
            is GRANDMASTER -> "GM1"
            is CHALLENGER -> "C1"
            else -> getTierAsString().capitalizeFirstLetterAndLowercaseRest() + " " +
                rank.romanToArabic()
        }
    }

    private fun getTierAsString() = this.javaClass.simpleName

    private fun String.capitalizeFirstLetterAndLowercaseRest(): String {
        return this.lowercase(Locale.getDefault()).replaceFirstChar { it.uppercase() }
    }

    companion object {
        fun getTierByRank(tier: String, rank: String): Tier {
            return when (tier) {
                "CHALLENGER" -> CHALLENGER(rank)
                "GRANDMASTER" -> GRANDMASTER(rank)
                "MASTER" -> MASTER(rank)
                "DIAMOND" -> DIAMOND(rank)
                "PLATINUM" -> PLATINUM(rank)
                "GOLD" -> GOLD(rank)
                "SILVER" -> SILVER(rank)
                "BRONZE" -> BRONZE(rank)
                "IRON" -> IRON(rank)
                else -> UNRANK
            }
        }
    }
}
