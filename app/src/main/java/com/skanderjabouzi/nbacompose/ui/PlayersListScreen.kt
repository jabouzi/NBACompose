package com.skanderjabouzi.nbacompose.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skanderjabouzi.nbacompose.R
import com.skanderjabouzi.nbacompose.components.PlayerListHeader
import com.skanderjabouzi.nbacompose.components.PlayerRow
import com.skanderjabouzi.nbacompose.models.network.Player
import com.skanderjabouzi.nbacompose.players.presentation.TeamPlayersViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersListScreen(
    modifier: Modifier = Modifier,
    teamPlayersViewModel: TeamPlayersViewModel = koinViewModel(),
    onBackClicked: (Int) -> Unit,
) {
    Log.e("TeamsListScreen", "TeamsListScreen()")
    val playersUiState by teamPlayersViewModel.players.collectAsStateWithLifecycle()
    var displayMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(id = R.string.players_list)) },
                    navigationIcon = {
                        IconButton(onClick = { onBackClicked(teamPlayersViewModel.teamId.toInt()) }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { displayMenu = !displayMenu }) {
                            Icon(Icons.Default.MoreVert, "")
                        }
                        DropdownMenu(expanded = displayMenu, onDismissRequest = { displayMenu = false }) {
                            DropdownMenuItem(text = { Text(stringResource(id = R.string.sort_by_name)) }, onClick = {
                                teamPlayersViewModel.sortByName()
                                displayMenu = !displayMenu
                            })
                            DropdownMenuItem(
                                text = { Text(stringResource(id = R.string.sort_by_position)) },
                                onClick = {
                                    teamPlayersViewModel.sortByPosition()
                                    displayMenu = !displayMenu
                                }
                            )
                            DropdownMenuItem(text = { Text(stringResource(id = R.string.sort_by_number)) }, onClick = {
                                teamPlayersViewModel.sortByNumber()
                                displayMenu = !displayMenu
                            })
                        }
                    }
                )
                PlayerListHeader()
            }
        },
        content = {
            PlayersList(modifier = modifier, playerList = playersUiState)
        }
    )
}

@Composable
fun PlayersList(
    playerList: List<Player>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 120.dp),
    ) {
        items(items = playerList) { player ->
            PlayerRow(player = player, onItemClicked = {})
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
