package com.skanderjabouzi.nbacompose.navigation.team

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.skanderjabouzi.nbacompose.navigation.navigateSingleTop
import com.skanderjabouzi.nbacompose.navigation.player.navigateToPlayers

const val TeamGraphNavigationRoute = "teamNavGraph"

fun NavController.navigateToDevicesGraph() {
    this.navigateSingleTop(TeamNavigationRoute)
}

fun NavGraphBuilder.teamGraph(
    navController: NavController,
) {
    navigation(
        startDestination = TeamsNavigationRoute,
        route = TeamGraphNavigationRoute
    ) {
        teamScreen(
            onBackClicked = {
                navController.popBackStack(TeamsNavigationRoute, inclusive = false)
            },
            onButtonClicked = {
                navController.navigateToPlayers(it)
            }
        )
        teamsScreen(
            onItemClicked = {
                navController.navigateToTeamDetails(it)
            }
        )
    }
}
