package com.skanderjabouzi.nbacompose.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skanderjabouzi.nbacompose.R

@Composable
fun BaseHeader(
    firstTitle: String,
    secondTitle: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(text = firstTitle)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row {
                Text(text = secondTitle)
            }
        }
    }
}

@Composable
fun PlayerListHeader() {
    BaseHeader(
        firstTitle = stringResource(id = R.string.position),
        secondTitle = stringResource(id = R.string.number),
    )
}

@Composable
fun TeamsListHeader() {
    BaseHeader(
        firstTitle = stringResource(id = R.string.wins),
        secondTitle = stringResource(id = R.string.losses),
    )
}

@Preview
@Composable
fun TestPlayerListHeader() {
    PlayerListHeader()
}

@Preview
@Composable
fun TestTeamsListHeader() {
    TeamsListHeader()
}
