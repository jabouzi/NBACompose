package com.skanderjabouzi.nbacompose.navigation

import com.skanderjabouzi.nbacompose.R

enum class TopLevelDestination(
    val iconTextId: Int
) {
    TEAMS(R.string.teams_list),
    TEAM(R.string.team),
    PLAYERS(R.string.players_list),
}