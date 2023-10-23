package com.skanderjabouzi.nbacompose.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skanderjabouzi.nbacompose.R
import com.skanderjabouzi.nbacompose.components.DisplayImage
import com.skanderjabouzi.nbacompose.helpers.TeamDetailsState
import com.skanderjabouzi.nbacompose.models.network.TeamDetails
import com.skanderjabouzi.nbacompose.team.presentation.TeamDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailsScreen(
    onBackClicked: () -> Unit,
    onButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    teamDetailsViewModel: TeamDetailsViewModel = koinViewModel(),
) {
    Log.e("TeamDetailsScreen", "TeamDetailsScreen()")
    val teamIdUiState by teamDetailsViewModel.teamDetails.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            Column() {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(id = R.string.team)) },
                    navigationIcon = {
                        IconButton(onClick = { onBackClicked() }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    },
                )
            }
        }
    ) {
        TeamDetails(
            modifier = modifier,
            teamDetailsState = teamIdUiState,
            onButtonClicked = onButtonClicked
        )
    }
}

@Composable
private fun TeamDetails(
    teamDetailsState: TeamDetailsState,
    modifier: Modifier = Modifier,
    onButtonClicked: (Int) -> Unit,
) {
    Log.e("TeamDetailsScreen", "$teamDetailsState")
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp)
    ) {
        TeamDetailInfo(
            team = teamDetailsState.team,
            onButtonClicked = onButtonClicked,
            modifier = modifier
        )
    }
}

@Composable
private fun TeamDetailInfo(
    team: TeamDetails?,
    modifier: Modifier = Modifier,
    onButtonClicked: (Int) -> Unit,
) {
    Log.e("TeamDetailsScreen", "$team")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DisplayImage(
            team?.imgURL.toString(),
            200.dp,
            200.dp
        )
        Row(modifier = Modifier.padding(top = 40.dp)) {
            Column(modifier = modifier.weight(1f)) {
                Text(text = team?.name.toString())
                Text(text = team?.region.toString())
            }
            Column(modifier = Modifier.weight(2f)) {
                Log.e("TeamDetailInfo", "teamId ${team?.id}")
                OutlinedButton(onClick = { team?.id?.let { onButtonClicked(it) } }) {
                    Text(text = stringResource(id = R.string.team_players))
                }
            }
            Column() {
                Text(text = team?.abbrev.toString())
                Text(text = team?.pop.toString())
            }
        }
    }
}

@Preview
@Composable
private fun TestTeamDetail() {
    TeamDetailInfo(
        team = TeamDetails(
            1,
            "Quebec",
            "Rocket",
            "RKT",
            8.7,
            "http:/skanderjabouzi.com/nbateamviewer/nba-dallas-mavericks-logo-300x300.png"
        ),
        onButtonClicked = {}
    )
}
