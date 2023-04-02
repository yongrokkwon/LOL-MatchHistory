package gg.lol.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gg.lol.android.ui.champion.ChampionScreen
import gg.lol.android.ui.community.CommunityScreen
import gg.lol.android.ui.esports.EsportsScreen
import gg.lol.android.ui.home.HomeScreen
import gg.lol.android.ui.main.MainViewModel
import gg.lol.android.ui.navigation.LOLMatchHistoryNavigationActions
import gg.lol.android.ui.navigation.LOLMatchHistoryRoute
import gg.lol.android.ui.navigation.TOP_LEVEL_DESTINATIONS
import gg.lol.android.ui.search.SearchScreen
import gg.lol.android.ui.setting.SettingScreen
import gg.lol.android.ui.theme.ColorBackground
import gg.lol.android.ui.view.AlertErrorDialog

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val navigationActions = remember { LOLMatchHistoryNavigationActions(navController) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: LOLMatchHistoryRoute.HOME
    when (val state = viewModel.uiState.collectAsState().value) {
        is UiState.Error -> AlertErrorDialog(throwable = state.error)
        else -> Unit
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavHost(Modifier.weight(1f), navController, viewModel)
        Divider(color = ColorBackground, thickness = 1.dp)
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
            contentColor = Color.White
        ) {
            TOP_LEVEL_DESTINATIONS.forEach { lolMatchHistoryDestination ->
                NavigationBarItem(
                    selected = selectedDestination == LOLMatchHistoryRoute.HOME,
                    onClick = { navigationActions.navigateTo(lolMatchHistoryDestination) },
                    icon = {
                        Icon(
                            imageVector = lolMatchHistoryDestination.selectedIcon,
                            contentDescription = stringResource(
                                id = lolMatchHistoryDestination.iconTextId
                            ),
                            modifier = Modifier
                                .background(color = Color.White)
                                .size(18.dp, 18.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color.White,
                        selectedTextColor = Color.Black,
                        indicatorColor = Color.White,
//                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.Black
                    ),
                    label = {
                        Text(
                            text = stringResource(id = lolMatchHistoryDestination.iconTextId),
                            fontSize = 11.sp
                        )
                    },
                    alwaysShowLabel = true
                )
            }
        }
    }
}

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel
) = NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = LOLMatchHistoryRoute.HOME
) {
    composable(LOLMatchHistoryRoute.HOME) {
        HomeScreen(navController, viewModel)
    }
    composable(LOLMatchHistoryRoute.CHAMPION) { ChampionScreen() }
    composable(LOLMatchHistoryRoute.ESPORTS) { EsportsScreen() }
    composable(LOLMatchHistoryRoute.COMMUNITY) { CommunityScreen() }
    composable(LOLMatchHistoryRoute.SETTING) { SettingScreen() }
    composable(LOLMatchHistoryRoute.SEARCH) { SearchScreen() }
}
