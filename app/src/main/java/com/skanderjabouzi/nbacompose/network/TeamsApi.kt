package com.skanderjabouzi.nbacompose.network

import com.skanderjabouzi.nbacompose.models.network.Players
import com.skanderjabouzi.nbacompose.models.network.TeamDetails
import com.skanderjabouzi.nbacompose.models.network.Teams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamsApi {

  @GET("teams.json")
  suspend fun getTeams(): Response<Teams>

  @GET("{team_id}.json")
  suspend fun getPlayers(@Path("team_id") teamId: Int): Response<Players>

  @GET("team_{team_id}.json")
  suspend fun getTeamDetails(@Path("team_id") teamId: Int): Response<TeamDetails>
}