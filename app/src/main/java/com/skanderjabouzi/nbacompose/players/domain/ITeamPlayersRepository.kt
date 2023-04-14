package com.skanderjabouzi.nbacompose.players.domain

import com.skanderjabouzi.nbacompose.models.db.PlayerEntity
import com.skanderjabouzi.nbacompose.models.network.Players
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ITeamPlayersRepository {
    suspend fun getSavedPlayers(teamId: Int): Flow<List<PlayerEntity>>

    suspend fun savePlayers(players: List<PlayerEntity>)

    suspend fun getPlayers(teamId: Int): Flow<Response<Players>>

    suspend fun getPlayerExists(id: Int): Int
}