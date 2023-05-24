package com.skanderjabouzi.nbacompose.navigation.team

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.skanderjabouzi.nbacompose.navigation.player.navigateToPlayers

const val TeamGraphNavigationRoute = "teamNavGraph"

fun NavController.navigateToDevicesGraph(navOptions: NavOptions? = null) {
    this.navigate(TeamNavigationRoute, navOptions)
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
                navController.popBackStack(TeamsNavigationRoute, inclusive = true)
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