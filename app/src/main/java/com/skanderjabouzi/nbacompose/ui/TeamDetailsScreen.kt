package com.skanderjabouzi.nbacompose.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skanderjabouzi.nbacompose.components.TeamRow
import com.skanderjabouzi.nbacompose.models.network.Team
import com.skanderjabouzi.nbacompose.teams.presentation.TeamsListViewModel
import com.skanderjabouzi.nbacompose.R
import com.skanderjabouzi.nbacompose.components.TeamsListHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailsScreen(
    modifier: Modifier = Modifier,
    teamId: Int,
    teamsListViewModel: TeamsListViewModel = viewModel()
) {
    Log.e("TeamsListScreen",  "TeamsListScreen()")
    val teamsUiState by teamsListViewModel.teams.collectAsState()
    var displayMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column() {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(id = R.string.teams_list)) },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    },
                )
            }
        },
        content =  {
            TeamDetails(modifier = modifier, teamsList = teamsUiState)
        }
    )
}

@Composable
fun TeamDetails(
    modifier: Modifier = Modifier,
    teamsList: List<Team>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp),
    ) {
        items(teamsList) { team ->
            TeamRow(modifier = modifier, team = team, onItemClicked = {})
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

}