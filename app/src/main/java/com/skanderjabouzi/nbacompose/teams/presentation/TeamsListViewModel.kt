package com.skanderjabouzi.nbacompose.teams.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skanderjabouzi.nbacompose.models.network.Team
import com.skanderjabouzi.nbacompose.teams.domain.TeamsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeamsListViewModel constructor(
    private val usecase: TeamsListUseCase,
) : ViewModel() {

    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams
    val error = usecase.error

    init {
        getTeams()
    }

    fun getTeams() {
        viewModelScope.launch {
            usecase.getTeams().collect {
                _teams.value = it as List<Team>
                Log.e("# TeamsListViewModel", "${teams.value}")
            }
        }
    }

    fun sortByName() {
        viewModelScope.launch {
            usecase.sortByName().collect {
                _teams.value = it
            }
        }
    }

    fun sortByWins() {
        viewModelScope.launch {
            usecase.sortByWins().collect {
                _teams.value = it
            }
        }
    }

    fun sortByLosses() {
        viewModelScope.launch {
            usecase.sortByLosses().collect {
                _teams.value = it
            }
        }
    }
}
