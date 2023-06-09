package com.skanderjabouzi.nbacompose.teams.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skanderjabouzi.nbacompose.hilt.DefaultDispatcher
import com.skanderjabouzi.nbacompose.models.network.Team
import com.skanderjabouzi.nbacompose.teams.domain.TeamsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsListViewModel @Inject constructor(
    private val usecase: TeamsListUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
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

