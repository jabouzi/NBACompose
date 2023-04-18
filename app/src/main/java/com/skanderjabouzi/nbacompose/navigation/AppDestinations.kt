package com.skanderjabouzi.nbacompose.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val name: String
    val route: String
}

object TeamsListDest : AppDestination {
    override val name = "Teams List"
    override val route = "teamsList"
}

object TeamDetailsDest : AppDestination {
    override val name = "Team Details"
    override val route = "teamDetails"
    const val teamIdArg = "teamId"
    val routeWithArgs = "$route/{$teamIdArg}"
    val arguments = listOf(
        navArgument(teamIdArg) {
            type = NavType.StringType
        }
    )
}

object TeamPlayersListDest : AppDestination {
    override val name = "Team Players List"
    override val route = "teamPlayersList"
    const val teamIdArg = "0"
}
