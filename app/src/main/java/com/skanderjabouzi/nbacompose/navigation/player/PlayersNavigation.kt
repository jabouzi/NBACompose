package com.skanderjabouzi.nbacompose.navigation.player

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.skanderjabouzi.nbacompose.navigation.TeamIdArg
import com.skanderjabouzi.nbacompose.navigation.navigateSingleTop
import com.skanderjabouzi.nbacompose.ui.PlayersListScreen

const val PlayersNavigationRoute = "players"

fun NavController.navigateToPlayers(teamId: Int) {
    this.navigateSingleTop(route = "$PlayersNavigationRoute/$teamId")
}

fun NavGraphBuilder.playersScreen(
    onBackClicked: (Int) -> Unit
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
            onBackClicked = onBackClicked
        )
    }
}
