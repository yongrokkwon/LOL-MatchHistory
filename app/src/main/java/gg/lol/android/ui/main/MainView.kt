package gg.lol.android.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gg.lol.android.ui.UiState
import gg.lol.android.ui.home.HomeView
import gg.lol.android.ui.match.MatchHistoryView
import gg.lol.android.ui.navigation.LOLMatchHistoryRoute
import gg.lol.android.ui.search.SearchView
import gg.lol.android.ui.summoner.MySummonerView
import gg.lol.android.ui.view.AlertErrorDialog

@Composable
fun MainView(viewModel: MainViewModel) {
    val navController = rememberNavController()
    when (val state = viewModel.uiState.collectAsState().value) {
        is UiState.Error -> AlertErrorDialog(throwable = state.error)
        else -> Unit
    }
    NavHost(modifier = Modifier.fillMaxSize(), navController)
}

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) = NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = LOLMatchHistoryRoute.Home.route
) {
    composable(LOLMatchHistoryRoute.Home.route) {
        HomeView(navController)
    }
    composable(LOLMatchHistoryRoute.Search.route) {
        SearchView(navController)
    }
    composable(LOLMatchHistoryRoute.MySummonerSearch.route) { backStackEntry ->
        MySummonerView(navController)
    }
    composable(LOLMatchHistoryRoute.MatchHistory.route) { backStackEntry ->
        MatchHistoryView(navController)
    }
}
