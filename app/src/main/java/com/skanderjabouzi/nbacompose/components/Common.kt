package com.skanderjabouzi.nbacompose.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skanderjabouzi.nbacompose.R

@Composable
fun DisplayImage(
    image: String,
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
) {
    Row() {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .clip(CircleShape)
                .size(width, height),
        )
    }
}
