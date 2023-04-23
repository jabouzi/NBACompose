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
            teamId?.toInt()?.let {
                TeamDetailsScreen(
                    onBackClicked = { navController.popBackStack() },
                    onButtonClicked = { navController.navigateToTeamPlayers(it) }
                )
            }
        }
        composable(
            route = TeamPlayersListDest.routeWithArgs,
            arguments = TeamPlayersListDest.arguments,
        ) { navBackStackEntry ->
            val teamId = navBackStackEntry.arguments?.getString(TeamPlayersListDest.teamIdArg)
            teamId?.toInt()?.let { PlayersListScreen(onBackClicked = { navController.popBackStack() }) }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String, doSaveState: Boolean = true) {
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = doSaveState
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun NavHostController.navigateToTeamDetails(teamId: Int) {
    this.navigateSingleTopTo("${TeamDetailsDest.route}/$teamId")
}

private fun NavHostController.navigateToTeamPlayers(teamId: Int) {
    this.navigateSingleTopTo("${TeamPlayersListDest.route}/$teamId", false)
}
