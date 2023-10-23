package com.skanderjabouzi.nbacompose.team.domain

import com.skanderjabouzi.nbacompose.models.db.TeamDetailsEntity
import com.skanderjabouzi.nbacompose.models.network.TeamDetails
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ITeamDetailsRepository {
    suspend fun getSavedTeamDetails(id: Int): Flow<TeamDetailsEntity>

    suspend fun saveTeamDetails(teamDetails: TeamDetailsEntity)

    suspend fun getTeamsDetails(id: Int): Flow<Response<TeamDetails>>

    suspend fun getTeamExists(id: Int): Int
}
