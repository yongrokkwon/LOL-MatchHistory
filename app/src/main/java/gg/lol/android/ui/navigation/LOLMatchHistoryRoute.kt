package gg.lol.android.ui.navigation

sealed class LOLMatchHistoryRoute(val route: String) {
    companion object {
        const val ARG_SUMMONER_NAME = "summonerName"
    }

    object Home : LOLMatchHistoryRoute(Home::class.java.simpleName)
    object Search : LOLMatchHistoryRoute(Search::class.java.simpleName)
    object MySummonerSearch : LOLMatchHistoryRoute(MySummonerSearch::class.java.simpleName)
    object MatchHistory : LOLMatchHistoryRoute(
        "${MatchHistory::class.java.simpleName}/{$ARG_SUMMONER_NAME}"
    ) {
        fun createRoute(summonerName: String?): String {
            return "${MatchHistory::class.java.simpleName}/$summonerName"
        }
    }
}
