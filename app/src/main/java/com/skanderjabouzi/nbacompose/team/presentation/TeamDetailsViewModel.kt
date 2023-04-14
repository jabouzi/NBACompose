package com.skanderjabouzi.nbacompose.team.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skanderjabouzi.nbacompose.helpers.TeamDetailsState
import com.skanderjabouzi.nbacompose.models.network.Team
import com.skanderjabouzi.nbacompose.models.network.TeamDetails
import com.skanderjabouzi.nbateamviewer.domain.usecase.TeamDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailsViewModel @Inject constructor(
    val usecase: TeamDetailsUseCase
) : ViewModel() {

    private val _team = MutableStateFlow<TeamDetailsState>(TeamDetailsState())
    val team: StateFlow<TeamDetailsState> = _team

//    private var _teamDetails = MutableLiveData<TeamDetails>()
//    val teamDetails:  LiveData<TeamDetails>
//        get() = _teamDetails
//    val error = usecase.error
//
//    fun getTeamDetails(id: Int) {
//        viewModelScope.launch {
//            _teamDetails.value = usecase.getTeamDetails(id)
//        }
//    }
}