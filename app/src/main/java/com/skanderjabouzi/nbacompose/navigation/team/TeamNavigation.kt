package com.skanderjabouzi.nbacompose.navigation.team

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.skanderjabouzi.nbacompose.navigation.TeamIdArg
import com.skanderjabouzi.nbacompose.navigation.navigateSingleTop
import com.skanderjabouzi.nbacompose.ui.TeamDetailsScreen

const val TeamNavigationRoute = "Team"

fun NavController.navigateToTeamDetails(teamId: Int) {
    this.navigateSingleTop(route = "$TeamNavigationRoute/$teamId")
}

fun NavGraphBuilder.teamScreen(
    onBackClicked: () -> Unit,
    onButtonClicked: (Int) -> Unit,
) {
    composable(
        route = "$TeamNavigationRoute/{$TeamIdArg}",
        arguments = listOf(
            navArgument(TeamIdArg) {
                type = NavType.StringType
            }
        )
    ) {
        TeamDetailsScreen(
            onBackClicked = onBackClicked,
            onButtonClicked = onButtonClicked
        )
    }
}
