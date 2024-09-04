package com.pborzikov.challenge.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pborzikov.challenge.R
import com.pborzikov.challenge.domian.models.MovieModel
import com.pborzikov.challenge.screens.CoilImageLoader
import timber.log.Timber


@Composable
fun MovieCard(
    movie: MovieModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            imageLoader = CoilImageLoader,
            model = movie.previewUrl,
            contentDescription = null,
            placeholder = painterResource(R.drawable.loading),
            error = painterResource(R.drawable.warning),

            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(width = 80.dp, height = 120.dp)
                .background(MaterialTheme.colorScheme.tertiary),
            onError = {
                Timber.e(it.result.throwable)
            },
        )

        Spacer(
            modifier = Modifier.width(20.dp),
        )

        Column {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = movie.voteAverage,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCard_Preview() {
    MovieCard(
        movie = MovieModel(
            id = 0,
            title = "Movie Title",
            previewUrl = "https://www.example.com/preview.jpg",
            releaseDate = "01.02.2003",
            voteAverage = "3.14",
        ),
    )
}
