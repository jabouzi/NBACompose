package com.skanderjabouzi.nbacompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skanderjabouzi.nbacompose.ui.PlayersListScreen
import com.skanderjabouzi.nbacompose.ui.TeamDetailsScreen
import com.skanderjabouzi.nbacompose.ui.TeamsListScreen

@Composable
fun NbaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = TeamsListDest.route,
        modifier = modifier
    ) {
        composable(route = TeamsListDest.route) {
            TeamsListScreen(onItemClicked = { teamId ->
                navController.navigateToTeamDetails(teamId)
            })
        }
        composable(
            route = TeamDetailsDest.routeWithArgs,
            arguments = TeamDetailsDest.arguments,
        ) { navBackStackEntry ->
            val teamId = navBackStackEntry.arguments?.getString(TeamDetailsDest.teamIdArg)
            teamId?.toInt()?.let { TeamDetailsScreen(navController = navController) }
        }
        composable(route = "${TeamPlayersListDest.route}/${TeamDetailsDest.teamIdArg}") {
            PlayersListScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun NavHostController.navigateToTeamDetails(teamId: Int) {
    this.navigateSingleTopTo("${TeamDetailsDest.route}/$teamId")
}
