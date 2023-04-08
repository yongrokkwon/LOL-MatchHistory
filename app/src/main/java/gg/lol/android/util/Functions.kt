package gg.lol.android.util

import androidx.lifecycle.SavedStateHandle
import kotlin.math.roundToInt

object Functions {

    fun <T> getArg(savedStateHandle: SavedStateHandle, argument: String): T? {
        return savedStateHandle.get<T>(argument)
    }

    fun calculateKda(kills: Int, deaths: Int, assists: Int): Double {
        val kda = (kills + assists) / deaths.toDouble()
        return if (kda.isNaN() || kda.isInfinite()) 0.0 else kda
    }

    fun calculateWinRate(wins: Int, losses: Int): Int {
        val totalGames = wins + losses
        if (totalGames == 0) {
            return 0
        }
        return (wins.toDouble() / totalGames.toDouble() * 100).toInt()
    }

    fun calculateKillInvolvementRate(kills: Int, assists: Int, totalKills: Int): Int {
        if (totalKills == 0) return 0
        val killContribution = kills + assists
        val killInvolvementRate = (killContribution.toDouble() / totalKills.toDouble()) * 100.0
        return killInvolvementRate.roundToInt()
    }
}
