package com.skanderjabouzi.nbacompose.players.domain

import android.util.Log
import com.skanderjabouzi.nbacompose.helpers.PlayerEntityAdapter
import com.skanderjabouzi.nbacompose.helpers.ResultState
import com.skanderjabouzi.nbacompose.helpers.SortType
import com.skanderjabouzi.nbacompose.helpers.UseCase
import com.skanderjabouzi.nbacompose.models.network.Player
import com.skanderjabouzi.nbacompose.models.network.Players
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class TeamPlayersUseCase @Inject constructor(
    val repository: ITeamPlayersRepository
) : UseCase() {
    suspend fun getTeamPlayers(teamId: Int): Flow<List<Player>> {
        val playerCount = repository.getPlayerExists(teamId)
        Log.e("playerCount", "playerCount $playerCount teamId $teamId")
        return if (playerCount > 0) {
            getLocalPlayers(teamId)
        } else {
            getRemotePlayers(teamId)
        }
    }

    private suspend fun getLocalPlayers(teamId: Int): Flow<List<Player>> {
        return repository.getSavedPlayers(teamId).map { dbList ->
            PlayerEntityAdapter.playerEntityListToPlayerList(dbList)
        }
    }

    private suspend fun getRemotePlayers(teamId: Int): Flow<List<Player>> {
        return repository.getPlayers(teamId).map { playersResponse ->
            val result = getRequestFromApi(playersResponse)
            if (result != null) {
                when (result) {
                    is ResultState.Success -> {
                        val players = (result.data as Players).players
                        if (players != null) {
                            savePlayersToDb(teamId, players)
                            players
                        } else {
                            emptyList()
                        }
                    } else -> emptyList()
                }
            } else {
                emptyList()
            }
        }
    }

    suspend fun sortByName(teamId: Int): Flow<List<Player>>  {
        sortName = getSortBy(sortName)
        return repository.getSavedPlayers(teamId).map { playersFlow ->
            if (sortName == SortType.ASCENDING) {
                PlayerEntityAdapter.playerEntityListToPlayerList(playersFlow)
                    .sortedWith(compareBy { it.full_name })
            } else {
                PlayerEntityAdapter.playerEntityListToPlayerList(playersFlow)
                    .sortedWith(compareByDescending { it.full_name })
            }
        }
    }

    suspend fun sortByPosition(teamId: Int): Flow<List<Player>> {
        sortPosition = getSortBy(sortPosition)
        return repository.getSavedPlayers(teamId).map { playersFlow ->
            if (sortPosition == SortType.ASCENDING) {
                PlayerEntityAdapter.playerEntityListToPlayerList(playersFlow)
                    .sortedWith(compareBy { it.position })
            } else {
                PlayerEntityAdapter.playerEntityListToPlayerList(playersFlow)
                    .sortedWith(compareByDescending { it.position })
            }
        }
    }

    suspend fun sortByNumber(teamId: Int): Flow<List<Player>> {
        sortNumber = getSortBy(sortNumber)
        return repository.getSavedPlayers(teamId).map { playersFlow ->
            if (sortNumber == SortType.ASCENDING) {
                PlayerEntityAdapter.playerEntityListToPlayerList(playersFlow)
                    .sortedWith(compareBy { it.number?.let { it1 -> convertToInt(it1) } })
            } else {
                PlayerEntityAdapter.playerEntityListToPlayerList(playersFlow)
                    .sortedWith(compareByDescending { it.number?.let { it1 -> convertToInt(it1) } })
            }
        }
    }

    private suspend fun savePlayersToDb(teamId: Int, players: List<Player>) {
        repository.savePlayers(PlayerEntityAdapter.playerListToPlayerEntityList(teamId, players))
    }

    private fun convertToInt(strNbr: String): Int {
        var res = 0
        try {
            res = strNbr.toInt()
        } catch (e: Exception) {
            Log.e("PlayersUseCase", e.localizedMessage)
        }

        return res
    }

    companion object {
        var sortName: SortType = SortType.ASCENDING
        var sortPosition: SortType = SortType.ASCENDING
        var sortNumber: SortType = SortType.ASCENDING
    }
}
