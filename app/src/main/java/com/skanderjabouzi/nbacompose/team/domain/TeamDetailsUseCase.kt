package com.skanderjabouzi.nbacompose.team.domain

import android.util.Log
import com.skanderjabouzi.nbacompose.helpers.ResultState
import com.skanderjabouzi.nbacompose.helpers.TeamDetailsEntityAdapter
import com.skanderjabouzi.nbacompose.helpers.UseCase
import com.skanderjabouzi.nbacompose.models.network.TeamDetails
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class TeamDetailsUseCase @Inject constructor(
    private val repository: ITeamDetailsRepository
) : UseCase() {

    suspend fun getTeamDetails(id: Int): Flow<TeamDetails?> {
        val teamExists = repository.getTeamExists(id)
        Log.e("TeamDetailsUseCase", "teamExists $teamExists")
        return if (teamExists > 0) {
            getLocalTeamDetails(id)
        } else {
            getRemoteTeamDetails(id)
        }
    }


    private suspend fun getLocalTeamDetails(id: Int): Flow<TeamDetails> {
        return repository.getSavedTeamDetails(id).map { teamDetails ->
            TeamDetailsEntityAdapter.teamDetailsEntityToTeamDetails(teamDetails)
        }
    }

    private suspend fun getRemoteTeamDetails(id: Int): Flow<TeamDetails?> {
        return repository.getTeamsDetails(id).map { response ->
            Log.e("TeamDetailsUseCase", "response ${response.body()}")
            val resultState = getRequestFromApi(response)
            Log.e("TeamDetailsUseCase", "resultState $resultState")
            if (resultState != null) {
                when (resultState) {
                    is ResultState.Success -> {
                        val details = (resultState.data as TeamDetails)
                        Log.e("TeamDetailsUseCase", "resultState $details")
                        saveTeamDetailsToDb(details)
                        details
                    }
                    else -> null
                }
            } else {
                null
            }
        }

    }

    private suspend fun saveTeamDetailsToDb(teamDetails: TeamDetails) {
        repository.saveTeamDetails(
            TeamDetailsEntityAdapter
                .teamDetailsToTeamDetailsEntity(teamDetails)
        )
    }
}
