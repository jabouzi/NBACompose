package com.skanderjabouzi.nbacompose.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.skanderjabouzi.nbacompose.R
import com.skanderjabouzi.nbacompose.components.TeamRow
import com.skanderjabouzi.nbacompose.helpers.TeamDetailsState
import com.skanderjabouzi.nbacompose.team.presentation.TeamDetailsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    teamDetailsViewModel: TeamDetailsViewModel = hiltViewModel(),
    //teamId: Int = 0,
) {
    Log.e("TeamDetailsScreen", "TeamDetailsScreen()")
    val teamIdUiState by teamDetailsViewModel.teamDetails.collectAsState()
    //teamDetailsViewModel.getTeamDetails(teamId)
    Scaffold(
        topBar = {
            Column() {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(id = R.string.team)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    },
                )
            }
        },
        content = {
            TeamDetails(modifier = modifier, teamDetailsState = teamIdUiState)
        }
    )
}

@Composable
fun TeamDetails(
    teamDetailsState: TeamDetailsState,
    modifier: Modifier = Modifier,

) {
    Text(
        text = teamDetailsState.team?.name.toString(),
        modifier = modifier.padding(10.dp),
    )
}
