package com.skanderjabouzi.nbacompose.helpers

import com.skanderjabouzi.nbacompose.models.network.Player
import com.skanderjabouzi.nbacompose.models.network.Team

sealed class ResultState {
    data class Success(val data: Any) : ResultState()
    data class Error(val error: String) : ResultState()
}

data class TeamsListState(
    val loading: Boolean = true,
    val teams: List<Team> = emptyList()
)

data class TeamDetailsState(
    val loading: Boolean = true,
    val team: Team? = null
)

data class TeamPlayersState(
    val loading: Boolean = true,
    val players: List<Player> = emptyList()
)