package com.skanderjabouzi.nbacompose.helpers

import com.skanderjabouzi.nbacompose.models.network.Player
import com.skanderjabouzi.nbacompose.models.network.Team
import com.skanderjabouzi.nbacompose.models.network.TeamDetails

sealed class ResultState {
    data class Success(val data: Any) : ResultState()
    data class Error(val error: String) : ResultState()
}

data class TeamsListState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val teams: List<Team> = emptyList()
)

data class TeamDetailsState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val team: TeamDetails? = null
)

data class TeamPlayersState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val players: List<Player> = emptyList()
)
