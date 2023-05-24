package com.skanderjabouzi.nbacompose.navigation.team

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.skanderjabouzi.nbacompose.navigation.navigateSingleTop
import com.skanderjabouzi.nbacompose.ui.TeamsListScreen

const val TeamsNavigationRoute = "Teams"

fun NavController.navigateToTeamsList(navOptions: NavOptions? = null) {
    this.navigateSingleTop(route = TeamsNavigationRoute, navOptions = navOptions)
}

fun NavGraphBuilder.teamsScreen(
    onItemClicked: (Int) -> Unit
) {
    composable(route = TeamsNavigationRoute) {
        TeamsListScreen(
            onItemClicked = onItemClicked
        )
    }
}
