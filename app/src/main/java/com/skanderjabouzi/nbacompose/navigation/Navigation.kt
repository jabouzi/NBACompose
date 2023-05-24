package com.skanderjabouzi.nbacompose.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import com.skanderjabouzi.nbacompose.navigation.player.playersScreen
import com.skanderjabouzi.nbacompose.navigation.team.TeamGraphNavigationRoute
import com.skanderjabouzi.nbacompose.navigation.team.teamGraph

const val TeamIdArg = "teamIdArg"
fun NavBackStackEntry.requireStringArg(argument: String) =
    arguments?.getString(argument) ?: error("Missing required argument: $argument")

@Composable
fun NbaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = TeamGraphNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        teamGraph(
            navController = navController,
        )
        playersScreen(
            navController = navController
        )
    }
}

fun NavController.navigateSingleTop(route: String, doSaveState: Boolean = true, navOptions: NavOptions? = null) {
    Log.e("####", route)
    this.navigate(
        route = route,
    ) {
        popUpTo(
            this@navigateSingleTop.graph.findStartDestination().id
        ) {
            saveState = doSaveState
        }
        launchSingleTop = true
        restoreState = true
    }
}
