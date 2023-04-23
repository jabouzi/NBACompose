package com.skanderjabouzi.nbacompose.network

import com.skanderjabouzi.nbacompose.models.network.Players
import com.skanderjabouzi.nbacompose.models.network.TeamDetails
import com.skanderjabouzi.nbacompose.models.network.Teams
import retrofit2.Response
import javax.inject.Inject

class RetrofitClient @Inject constructor(private val teamsApi: TeamsApi) {

    suspend fun getTeams(): Response<Teams> {
        return teamsApi.getTeams()
    }

    suspend fun getPlayers(teamId: Int): Response<Players> {
        return teamsApi.getPlayers(teamId)
    }

    suspend fun getTeamDetails(teamId: Int): Response<TeamDetails> {
        return teamsApi.getTeamDetails(teamId)
    }
}
