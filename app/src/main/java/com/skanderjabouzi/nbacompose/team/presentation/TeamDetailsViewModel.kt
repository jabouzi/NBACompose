package com.skanderjabouzi.nbacompose.team.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skanderjabouzi.nbacompose.helpers.TeamDetailsState
import com.skanderjabouzi.nbacompose.models.network.TeamDetails
import com.skanderjabouzi.nbacompose.navigation.TeamDetailsDest
import com.skanderjabouzi.nbateamviewer.domain.usecase.TeamDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val usecase: TeamDetailsUseCase
) : ViewModel() {

    private val _teamDetails = MutableStateFlow<TeamDetailsState>(TeamDetailsState())
    val teamDetails: StateFlow<TeamDetailsState> = _teamDetails

    init {
        val teamId = checkNotNull(savedStateHandle.get<String>(TeamDetailsDest.teamIdArg))
        getTeamDetails(teamId.toInt())
    }

    fun getTeamDetails(id: Int) {
        viewModelScope.launch {
            usecase.getTeamDetails(id).collect() { result ->
                if (result != null) {
                    handleSuccess(result)
                } else {
                    handleFailure()
                }
            }
        }
    }

    private fun handleSuccess(teamDetails: TeamDetails) {
        _teamDetails.value = TeamDetailsState(loading = false, team = teamDetails)
    }

    private fun handleFailure() {
        _teamDetails.value = TeamDetailsState(loading = false, error = true)
    }
}
