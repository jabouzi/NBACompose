package com.skanderjabouzi.nbacompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skanderjabouzi.nbacompose.R
import com.skanderjabouzi.nbacompose.components.TeamRow
import com.skanderjabouzi.nbacompose.components.TeamsListHeader
import com.skanderjabouzi.nbacompose.models.network.Team
import com.skanderjabouzi.nbacompose.teams.presentation.TeamsListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeamsListScreen(
    modifier: Modifier = Modifier,
    teamsListViewModel: TeamsListViewModel = koinViewModel(),
    onItemClicked: (Int) -> Unit
) {
    val teamsUiState by teamsListViewModel.teams.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppBar(modifier = modifier, teamsListViewModel = teamsListViewModel)
        }
    ) { innerPadding ->
        TeamList(
            contentPadding = innerPadding,
            teamsList = teamsUiState,
            onItemClicked = onItemClicked,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    teamsListViewModel: TeamsListViewModel,
    modifier: Modifier = Modifier,
) {
    var displayMenu by remember { mutableStateOf(false) }
    Column {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(id = R.string.teams_list)) },
            actions = {
                IconButton(onClick = { displayMenu = !displayMenu }) {
                    Icon(
                        Icons.Default.MoreVert,
                        null,
                        modifier = modifier
                    )
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
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamList(
    contentPadding: PaddingValues,
    teamsList: List<Team>, // ImmutableList
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
            .fillMaxSize(),
    ) {
        stickyHeader {
            TeamsListHeader()
        }
        items(items = teamsList) { team ->
            TeamRow(team = team, onItemClicked = { teamId ->
                if (teamId != null) {
                    onItemClicked(teamId)
                }
            })
            Divider(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                thickness = 0.5.dp,
                color = Color.DarkGray,
            )
        }
    }
}
