package com.skanderjabouzi.nbacompose.players.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skanderjabouzi.nbacompose.models.network.Player
import com.skanderjabouzi.nbacompose.navigation.TeamDetailsDest
import com.skanderjabouzi.nbacompose.navigation.TeamPlayersListDest
import com.skanderjabouzi.nbacompose.players.domain.TeamPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamPlayersViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val usecase: TeamPlayersUseCase
) : ViewModel() {

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players
    var teamId: String

    init {
        teamId = checkNotNull(savedStateHandle.get<String>(TeamPlayersListDest.teamIdArg))
        getPlayers()
    }
    fun getPlayers() {
        viewModelScope.launch {
            usecase.getTeamPlayers(teamId.toInt()).collect {
                _players.value = it
            }
        }
    }

    fun sortByName() {
        viewModelScope.launch {
            usecase.sortByName(teamId.toInt()).collect {
                _players.value = it
            }
        }
    }

    fun sortByPosition() {
        viewModelScope.launch {
            usecase.sortByPosition(teamId.toInt()).collect {
                _players.value = it
            }
        }
    }

    fun sortByNumber() {
        viewModelScope.launch {
            usecase.sortByNumber(teamId.toInt()).collect {
                _players.value = it
            }
        }
    }
}
