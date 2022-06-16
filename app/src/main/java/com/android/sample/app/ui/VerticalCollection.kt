package com.android.sample.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.android.sample.app.R
import com.android.sample.app.domain.Movie
import com.android.sample.app.domain.Movies

@Composable
fun VerticalCollection(
    item: Movies,
    onItemClick: (Movie) -> Unit
) {
    LazyColumn {
        items(
            items = item.movies,
            itemContent = { movie ->
                VerticalListItem(
                    baseImageUrl = item.imageBase,
                    movie = movie,
                    onItemClick = onItemClick
                )
                ListItemDivider()
            }
        )
    }
}

@Composable
private fun VerticalListItem(
    baseImageUrl: String,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { onItemClick(movie) })
    ) {
        Image(
            painter = rememberImagePainter(baseImageUrl.plus(movie.image)),
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = movie.price,
            style = typography.h6
        )
        Text(
            text = if(movie.available) stringResource(R.string.available)
            else stringResource(R.string.not_available),
            style = typography.body2
        )
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}