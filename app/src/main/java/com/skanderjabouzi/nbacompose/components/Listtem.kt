package com.skanderjabouzi.nbacompose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skanderjabouzi.nbacompose.R
import com.skanderjabouzi.nbacompose.models.network.Player
import com.skanderjabouzi.nbacompose.models.network.Team


@Composable
fun TeamRow(
    team: Team,
    onItemClicked: (team: Team) -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseRow(
        title = team.name.toString(),
        firstValue = team.wins.toString(),
        secondValue = team.losses.toString(),
        onItemClicked = { onItemClicked(team) },
        modifier = modifier,
        item = team,
        image = team.imgURL.toString(),
        imageVisible = true,
    )
}

@Composable
fun PlayerRow(
    player: Player,
    onItemClicked: (player: Player) -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseRow(
        title = player.full_name.toString(),
        firstValue = player.position.toString(),
        secondValue = player.number.toString(),
        onItemClicked = { onItemClicked(player) },
        modifier = modifier,
        item = player,
        image = "",
        imageVisible = false,
    )
}

@Composable
fun BaseRow(
    title: String,
    firstValue: String,
    secondValue: String,
    image: String,
    imageVisible: Boolean,
    onItemClicked: (any: Any) -> Unit,
    modifier: Modifier = Modifier,
    item: Any,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClicked(item) }),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
            ) {
                if (imageVisible) {
                Row(
                ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.placeholder),
                            error = painterResource(R.drawable.placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(40.dp, 40.dp),
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Row() {
                    Text(text = title)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Row() {
                        Text(text = firstValue)
                    }
                    Spacer(modifier = Modifier.width(36.dp))
                    Row() {
                        Text(text = secondValue)
                    }
                }
            }
        }
        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
        )
    }
}


@Preview
@Composable
fun TestTeamItem() {
    val team = Team(id = 1, name = "The Team", wins = 10, losses = 2, imgURL = "")
    TeamRow(team = team, onItemClicked = {})
}

@Preview
@Composable
fun TestPlayerItem() {
    val player = Player(
        position = "RW",
        number = "99",
        full_name = "Skander Jabouzi",
        height = "2m00",
        weight = "200",
        date_of_birth = "10-10-1980",
        from = "NY"
    )
    PlayerRow(player = player, onItemClicked = {})
}