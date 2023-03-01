/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gg.lol.android.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import gg.lol.android.IconPack
import gg.lol.android.R
import gg.lol.android.iconpack.*

// TODO selaed class refactor
object LOLGGRoute {
    const val HOME = "홈"
    const val CHAMPION = "챔피언"
    const val ESPORTS = "이스포츠"
    const val COMMUNITY = "커뮤니티"
    const val SETTING = "설정"
    const val SEARCH = "SEARCH"
    const val SIGNUP = "회원가입"
}

sealed class LOLGGTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
) {
    object Home : LOLGGTopLevelDestination(
        route = LOLGGRoute.HOME,
        selectedIcon = IconPack.Home,
        unselectedIcon = IconPack.Home,
        iconTextId = R.string.home
    )
    object Champion : LOLGGTopLevelDestination(
        route = LOLGGRoute.CHAMPION,
        selectedIcon = IconPack.Champion,
        unselectedIcon = IconPack.Champion,
        iconTextId = R.string.champion
    )
    object Esports : LOLGGTopLevelDestination(
        route = LOLGGRoute.ESPORTS,
        selectedIcon = IconPack.Esports,
        unselectedIcon = IconPack.Esports,
        iconTextId = R.string.esports
    )
    object Community : LOLGGTopLevelDestination(
        route = LOLGGRoute.COMMUNITY,
        selectedIcon = IconPack.Community,
        unselectedIcon = IconPack.Community,
        iconTextId = R.string.community
    )
    object Setting : LOLGGTopLevelDestination(
        route = LOLGGRoute.SETTING,
        selectedIcon = IconPack.Setting,
        unselectedIcon = IconPack.Setting,
        iconTextId = R.string.setting
    )
}

class LOLGGNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: LOLGGTopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

val TOP_LEVEL_DESTINATIONS = listOf(
    LOLGGTopLevelDestination.Home,
    LOLGGTopLevelDestination.Champion,
    LOLGGTopLevelDestination.Esports,
    LOLGGTopLevelDestination.Community,
    LOLGGTopLevelDestination.Setting,
)