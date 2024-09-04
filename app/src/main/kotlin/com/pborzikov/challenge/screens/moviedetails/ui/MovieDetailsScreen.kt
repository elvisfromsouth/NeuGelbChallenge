package com.pborzikov.challenge.screens.moviedetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.pborzikov.challenge.R
import com.pborzikov.challenge.designsystem.components.InfoItem
import com.pborzikov.challenge.designsystem.components.PageLoader
import com.pborzikov.challenge.designsystem.components.PageLoadingError
import com.pborzikov.challenge.screens.CoilImageLoader
import com.pborzikov.challenge.screens.moviedetails.MovieDetailsEvent
import com.pborzikov.challenge.screens.moviedetails.MovieDetailsUiState


@Composable
fun MovieDetailsScreen(
    uiState: MovieDetailsUiState,
    handleEvent: (event: MovieDetailsEvent) -> Unit,
) {
    LaunchedEffect(Unit) {
        handleEvent(MovieDetailsEvent.OnStart)
    }

    if (uiState.isLoading) {
        PageLoader(
            modifier = Modifier.fillMaxSize(),
        )
        return
    }

    if (!uiState.errorMessage.isNullOrEmpty()) {
        PageLoadingError(
            modifier = Modifier.fillMaxSize(),
            errorMessage = uiState.errorMessage,
            onClick = remember { { handleEvent(MovieDetailsEvent.RetryRequest) } },
        )
        return
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(
                horizontal = 15.dp,
                vertical = 5.dp,
            ),
    ) {
        AsyncImage(
            imageLoader = CoilImageLoader,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(
                    width = 140.dp,
                    height = 210.dp,
                )
                .background(MaterialTheme.colorScheme.tertiary),
            model = uiState.posterPath,
            contentDescription = null,
            placeholder = painterResource(R.drawable.loading),
            error = painterResource(R.drawable.warning),
        )

        Spacer(modifier = Modifier.size(12.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium,
            text = uiState.title,
        )

        Spacer(modifier = Modifier.size(24.dp))

        uiState.movieDetails.forEach { detail ->
            InfoItem(
                title = stringResource(detail.title),
                description = detail.description,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MovieDetailsScreen_Preview() {
    MovieDetailsScreen(
        uiState = MovieDetailsUiState(
            imageLoader = ImageLoader.Builder(LocalContext.current).build(),
        ),
        handleEvent = {},
    )
}
