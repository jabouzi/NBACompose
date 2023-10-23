package com.skanderjabouzi.nbacompose.teams.domain

import android.util.Log
import com.skanderjabouzi.nbacompose.helpers.ResultState
import com.skanderjabouzi.nbacompose.helpers.SortType
import com.skanderjabouzi.nbacompose.helpers.TeamEntityAdapter
import com.skanderjabouzi.nbacompose.helpers.UseCase
import com.skanderjabouzi.nbacompose.models.network.Team
import com.skanderjabouzi.nbacompose.models.network.Teams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamsListUseCase constructor(
    private val repository: ITeamsRepository,
) : UseCase() {

    suspend fun getTeams(): Flow<List<Team>> {
        val teamsCount = repository.getTeamsCount()
        Log.e("# TeamsListUseCase 1", "$teamsCount")
        return if (teamsCount > 0) {
            getLocalTeams()
        } else {
            getRemoteTeams()
        }
    }

    private suspend fun getLocalTeams(): Flow<List<Team>> {
        return repository.getSavedTeams().map { databaseList ->
            Log.e("# TeamsListUseCase 2", "$databaseList")
            TeamEntityAdapter.teamEntityListToTeamList(databaseList)
        }
    }

    private suspend fun getRemoteTeams(): Flow<List<Team>> {
        return repository.getTeams().map { teamsResponse ->
            Log.e("# TeamsListUseCase 3", "$teamsResponse")
            val resultState = getRequestFromApi(teamsResponse)
            if (resultState != null) {
                when (resultState) {
                    is ResultState.Success -> {
                        val teams = (resultState.data as Teams).teams
                        if (teams != null) {
                            Log.e("# TeamsListUseCase 4", "$teams")
                            saveTeamsToDb(teams)
                            teams
                        } else {
                            emptyList()
                        }
                    }
                    else -> emptyList()
                }
            } else {
                emptyList()
            }
        }
    }

    suspend fun sortByName(): Flow<List<Team>> {
        sortName = getSortBy(sortName)
        return repository.getSavedTeams().map { teamsFlow ->
            if (sortName == SortType.ASCENDING) {
                TeamEntityAdapter.teamEntityListToTeamList(teamsFlow)
                    .sortedWith(compareBy { it.name })
            } else {
                TeamEntityAdapter.teamEntityListToTeamList(teamsFlow)
                    .sortedWith(compareByDescending { it.name })
            }
        }
    }

    suspend fun sortByWins(): Flow<List<Team>> {
        sortWins = getSortBy(sortWins)
        return repository.getSavedTeams().map { teamsFlow ->
            if (sortWins == SortType.ASCENDING) {
                TeamEntityAdapter.teamEntityListToTeamList(teamsFlow)
                    .sortedWith(compareBy { it.wins })
            } else {
                TeamEntityAdapter.teamEntityListToTeamList(teamsFlow)
                    .sortedWith(compareByDescending { it.wins })
            }
        }
    }

    suspend fun sortByLosses(): Flow<List<Team>> {
        sortLosses = getSortBy(sortLosses)
        return repository.getSavedTeams().map { teamsFlow ->
            if (sortLosses == SortType.ASCENDING) {
                TeamEntityAdapter.teamEntityListToTeamList(teamsFlow)
                    .sortedWith(compareBy { it.losses })
            } else {
                TeamEntityAdapter.teamEntityListToTeamList(teamsFlow)
                    .sortedWith(compareByDescending { it.losses })
            }
        }
    }

    private suspend fun saveTeamsToDb(teams: List<Team>) {
        repository.saveTeams(TeamEntityAdapter.teamListToTeamEntityList(teams))
    }

    companion object {
        var sortName: SortType = SortType.ASCENDING
        var sortWins: SortType = SortType.ASCENDING
        var sortLosses: SortType = SortType.ASCENDING
    }
}
