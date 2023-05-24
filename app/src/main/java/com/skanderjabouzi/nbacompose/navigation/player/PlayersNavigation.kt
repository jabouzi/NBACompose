package com.skanderjabouzi.nbacompose.navigation.player

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.skanderjabouzi.nbacompose.navigation.TeamIdArg
import com.skanderjabouzi.nbacompose.navigation.navigateSingleTop
import com.skanderjabouzi.nbacompose.navigation.team.TeamNavigationRoute
import com.skanderjabouzi.nbacompose.ui.PlayersListScreen

const val PlayersNavigationRoute = "players"

fun NavController.navigateToPlayers(teamId: Int, navOptions: NavOptions? = null) {
    this.navigateSingleTop(route = "$PlayersNavigationRoute/$teamId", navOptions = navOptions)
}

fun NavGraphBuilder.playersScreen(
    navController: NavController,
) {
    composable(
        route = "$PlayersNavigationRoute/{$TeamIdArg}",
        arguments = listOf(
            navArgument(TeamIdArg) {
                type = NavType.StringType
            }
        )
    ) {
        PlayersListScreen(
            onBackClicked = {
                navController.popBackStack(
                    route = TeamNavigationRoute,
                    inclusive = true
                )
            }
        )
    }
}
