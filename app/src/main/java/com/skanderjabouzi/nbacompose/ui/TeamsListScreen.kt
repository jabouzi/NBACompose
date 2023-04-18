package com.skanderjabouzi.nbacompose.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skanderjabouzi.nbacompose.R
import com.skanderjabouzi.nbacompose.components.TeamRow
import com.skanderjabouzi.nbacompose.components.TeamsListHeader
import com.skanderjabouzi.nbacompose.models.network.Team
import com.skanderjabouzi.nbacompose.teams.presentation.TeamsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsListScreen(
    modifier: Modifier = Modifier,
    teamsListViewModel: TeamsListViewModel = hiltViewModel(),
    onItemClicked: (Int) -> Unit
) {
    Log.e("TeamsListScreen", "TeamsListScreen()")
    val teamsUiState by teamsListViewModel.teams.collectAsStateWithLifecycle()
    var displayMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column() {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(id = R.string.teams_list)) },
                    actions = {
                        IconButton(onClick = { displayMenu = !displayMenu }) {
                            Icon(Icons.Default.MoreVert, "")
                        }

                        DropdownMenu(expanded = displayMenu, onDismissRequest = { displayMenu = false }) {
                            DropdownMenuItem(text = { Text(stringResource(id = R.string.sort_by_name)) }, onClick = {
                                teamsListViewModel.sortByName()
                                displayMenu = !displayMenu
                            })
                            DropdownMenuItem(text = { Text(stringResource(id = R.string.sort_by_wins)) }, onClick = {
                                teamsListViewModel.sortByWins()
                                displayMenu = !displayMenu
                            })
                            DropdownMenuItem(text = { Text(stringResource(id = R.string.sort_by_losses)) }, onClick = {
                                teamsListViewModel.sortByLosses()
                                displayMenu = !displayMenu
                            })
                        }
                    }
                )
                TeamsListHeader()
            }
        },
        content = {
            TeamList(
                modifier = modifier,
                teamsList = teamsUiState,
                onItemClicked = onItemClicked
            )
        }
    )
}

@Composable
fun TeamList(
    teamsList: List<Team>, // ImmutableList
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 120.dp),
    ) {
        items(teamsList) { team ->
            TeamRow(team = team, onItemClicked = { teamId ->
                if (teamId != null) {
                    onItemClicked(teamId)
                }
            })
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
