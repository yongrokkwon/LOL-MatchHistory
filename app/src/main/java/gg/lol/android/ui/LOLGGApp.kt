package gg.lol.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
import gg.lol.android.ui.navigation.LOLGGNavigationActions
import gg.lol.android.ui.navigation.LOLGGRoute
import gg.lol.android.ui.navigation.TOP_LEVEL_DESTINATIONS
import gg.lol.android.ui.setting.SettingScreen
import gg.lol.android.ui.theme.ColorBackground

@Preview
@Composable
fun LOLGGApp() {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        LOLGGNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: LOLGGRoute.HOME

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavHost(
            Modifier.weight(1f),
            navController
        )
        Divider(color = ColorBackground, thickness = 1.dp)
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
            contentColor = Color.White
        ) {
            TOP_LEVEL_DESTINATIONS.forEach { lolggDestination ->
                NavigationBarItem(
                    selected = selectedDestination == LOLGGRoute.HOME,
                    onClick = { navigationActions.navigateTo(lolggDestination) },
                    icon = {
                        Icon(
                            imageVector = lolggDestination.selectedIcon,
                            contentDescription = stringResource(id = lolggDestination.iconTextId),
                            modifier = Modifier.background(color = Color.White).size(18.dp, 18.dp),
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color.White,
                        selectedTextColor = Color.Black,
                        indicatorColor = Color.White,
//                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.Black,
                    ),
                    label = {
                        Text(
                            text = stringResource(id = lolggDestination.iconTextId),
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
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = LOLGGRoute.HOME
    ) {
        composable(LOLGGRoute.HOME) {
            HomeScreen()
        }
        composable(LOLGGRoute.CHAMPION) {
            ChampionScreen()
        }
        composable(LOLGGRoute.ESPORTS) {
            EsportsScreen()
        }
        composable(LOLGGRoute.COMMUNITY) {
            CommunityScreen()
        }
        composable(LOLGGRoute.SETTING) {
            SettingScreen()
        }
    }
}