package com.skanderjabouzi.nbacompose.players.data

import com.skanderjabouzi.nbacompose.database.PlayersDao
import com.skanderjabouzi.nbacompose.hilt.IODispatcher
import com.skanderjabouzi.nbacompose.models.db.PlayerEntity
import com.skanderjabouzi.nbacompose.models.network.Players
import com.skanderjabouzi.nbacompose.network.RetrofitClient
import com.skanderjabouzi.nbacompose.players.domain.ITeamPlayersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class TeamPlayersRepository @Inject constructor(
  private val retrofitClient: RetrofitClient,
  private val playersDao: PlayersDao,
  @IODispatcher private val dispatcher: CoroutineDispatcher,
) : ITeamPlayersRepository {

  override suspend fun getSavedPlayers(teamId: Int): Flow<List<PlayerEntity>> {
    return withContext(dispatcher) {
      playersDao.getPlayers(teamId)
    }
  }

  override suspend fun savePlayers(players: List<PlayerEntity>) {
    withContext(dispatcher) {
      for (player in players) {
        playersDao.insert(player)
      }
    }
  }

  override suspend fun getPlayers(teamId: Int): Flow<Response<Players>> {
    return flow {
      retrofitClient.getPlayers(teamId)
    }
  }

  override suspend fun getPlayerExists(id: Int): Int {
    return withContext(dispatcher) {
      playersDao.getPlayerExists(id)
    }
  }
}