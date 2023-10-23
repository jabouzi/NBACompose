package com.skanderjabouzi.nbacompose.team.data

import com.skanderjabouzi.nbacompose.database.TeamDao
import com.skanderjabouzi.nbacompose.di.IODispatcher
import com.skanderjabouzi.nbacompose.models.db.TeamDetailsEntity
import com.skanderjabouzi.nbacompose.models.network.TeamDetails
import com.skanderjabouzi.nbacompose.network.RetrofitClient
import com.skanderjabouzi.nbacompose.team.domain.ITeamDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class TeamDetailsRepository constructor(
    private val retrofitClient: RetrofitClient,
    private val teamDao: TeamDao,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : ITeamDetailsRepository {

    override suspend fun getSavedTeamDetails(id: Int): Flow<TeamDetailsEntity> {
        return withContext(dispatcher) {
            teamDao.getTeamDetails(id)
        }
    }

    override suspend fun saveTeamDetails(teamDetails: TeamDetailsEntity) {
        withContext(dispatcher) {
            teamDao.insert(teamDetails)
        }
    }

    override suspend fun getTeamsDetails(id: Int): Flow<Response<TeamDetails>> {
        return flow {
            emit(retrofitClient.getTeamDetails(id))
        }
    }

    override suspend fun getTeamExists(id: Int): Int {
        return withContext(dispatcher) {
            teamDao.getTeamExists(id)
        }
    }
}
