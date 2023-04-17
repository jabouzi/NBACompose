package com.skanderjabouzi.nbacompose.navigation

import androidx.compose.runtime.Composable
import com.skanderjabouzi.nbacompose.ui.PlayersListScreen
import com.skanderjabouzi.nbacompose.ui.TeamDetailsScreen
import com.skanderjabouzi.nbacompose.ui.TeamsListScreen

interface AppDestination {
    val name: String
    val route: String
    val screen: @Composable () -> Unit
}

object TeamsListDestination: AppDestination {
    override val name = "Teams List"
    override val route = "teamsList"
    override val screen: @Composable () -> Unit = { TeamsListScreen() }
}

object TeamDetailsDestination : AppDestination {
    override val name = "Team Details"
    override val route = "teamDetails"
    override val screen: @Composable () -> Unit = { TeamDetailsScreen() }
    const val teamIdArg = "0"
}

object TeamPlayersListDestination : AppDestination {
    override val name = "Team Players List"
    override val route = "teamPlayersList"
    override val screen: @Composable () -> Unit = { PlayersListScreen() }
    const val teamIdArg = "0"
}