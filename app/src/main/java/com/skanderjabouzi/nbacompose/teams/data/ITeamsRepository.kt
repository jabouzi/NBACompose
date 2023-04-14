package com.skanderjabouzi.nbacompose.teams.data

import com.skanderjabouzi.nbacompose.models.db.TeamEntity
import com.skanderjabouzi.nbacompose.models.network.Teams
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ITeamsRepository {
    suspend fun getSavedTeams(): Flow<List<TeamEntity>>

    suspend fun saveTeams(teams: List<TeamEntity>)

    suspend fun getTeams(): Flow<Response<Teams>>

    suspend fun getTeamsCount(): Int
}