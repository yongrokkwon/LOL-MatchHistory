package gg.op.lol.domain.models

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
        return "${this.javaClass.simpleName.capitalizeFirstLetterAndLowercaseRest()} $rank"
    }

    fun toSummaryName(): String {
        return when (this) {
            is MASTER -> "M 1"
            is GRANDMASTER -> "GM 1"
            is CHALLENGER -> "C 1"
            else -> "${this.javaClass.simpleName.capitalizeFirstLetterAndLowercaseRest()} $rank"
        }
    }

    private fun String.capitalizeFirstLetterAndLowercaseRest(): String {
        return this.lowercase(Locale.getDefault()).replaceFirstChar { it.uppercase() }
    }

    companion object {
        // TODO
        fun valueOf(tier: String, rank: String): Tier {
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
