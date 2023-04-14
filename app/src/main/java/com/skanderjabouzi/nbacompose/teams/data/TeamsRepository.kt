package com.skanderjabouzi.nbacompose.teams.data

import android.util.Log
import com.skanderjabouzi.nbacompose.database.TeamDao
import com.skanderjabouzi.nbacompose.hilt.IODispatcher
import com.skanderjabouzi.nbacompose.models.db.TeamEntity
import com.skanderjabouzi.nbacompose.models.network.Teams
import com.skanderjabouzi.nbacompose.network.RetrofitClient
import com.skanderjabouzi.nbacompose.teams.domain.ITeamsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class TeamsRepository @Inject constructor(
  private val retrofitClient: RetrofitClient,
  private val teamDao: TeamDao,
  @IODispatcher private val dispatcher: CoroutineDispatcher,
) : ITeamsRepository {
  override suspend fun getSavedTeams(): Flow<List<TeamEntity>> {
    return withContext(dispatcher) {
      val temp = teamDao.getTeams()
      Log.e("# TeamsRepository 1", "$temp")
      temp
    }
  }

  override suspend fun saveTeams(teams: List<TeamEntity>) {
    withContext(dispatcher) {
      for (team in teams) {
        teamDao.insert(team)
      }
    }
  }

  override suspend fun getTeams(): Flow<Response<Teams>> {
    val teams = retrofitClient.getTeams()
    Log.e("# TeamsRepository 2","${teams.body()}")
    return flow {
      emit(teams)
    }
  }

  override suspend fun getTeamsCount(): Int {
    return withContext(dispatcher) {
      teamDao.getTeamsCount()
    }
  }
}