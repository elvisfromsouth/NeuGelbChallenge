package com.pborzikov.challenge.screens.movieslist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import com.pborzikov.challenge.R
import com.pborzikov.challenge.designsystem.components.AutoCompleteTextField
import com.pborzikov.challenge.designsystem.components.MovieCard
import com.pborzikov.challenge.designsystem.components.NextPageLoading
import com.pborzikov.challenge.designsystem.components.NextPageLoadingError
import com.pborzikov.challenge.designsystem.components.PageLoader
import com.pborzikov.challenge.designsystem.components.PageLoadingError
import com.pborzikov.challenge.domian.models.MovieModel
import com.pborzikov.challenge.screens.movieslist.MoviesListEvent
import com.pborzikov.challenge.screens.movieslist.MoviesListUiState
import kotlinx.coroutines.flow.Flow


@Composable
fun MoviesListScreen(
    uiState: MoviesListUiState,
    handleEvent: (event: MoviesListEvent) -> Unit,
) {
    LaunchedEffect(Unit) {
        handleEvent(MoviesListEvent.OnStart)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        AutoCompleteTextField(
            text = uiState.movieSearchQuery,
            onValueChange = remember {
                { value ->
                    handleEvent(MoviesListEvent.OnSearch(value))
                }
            },
            suggestions = uiState.movieSearchResult,
            onSuggestionSelected = { index ->
                handleEvent(
                    MoviesListEvent.OnSuggestClicked(
                        uiState.movieSearchResult[index].id,
                    ),
                )
            },
            item = {
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            label = {
                Text(text = stringResource(R.string.search))
            },
        )

        Spacer(
            modifier = Modifier.height(12.dp),
        )

        PagingItems(
            moviesPaging = uiState.moviesPaging,
            onItemSelected = remember {
                { selectedMovie ->
                    handleEvent(MoviesListEvent.OnMovieClicked(selectedMovie.id))
                }
            },
        )

    }
}

@Composable
private fun PagingItems(
    moviesPaging: Flow<PagingData<MovieModel>>,
    onItemSelected: (MovieModel) -> Unit,
) {
    val moviePagingItems = moviesPaging.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            key = { index: Int -> moviePagingItems[index]!!.id },
            count = moviePagingItems.itemCount,
        ) { index ->
            val movie = moviePagingItems[index]!!
            val clickableModifier = remember(movie) {
                Modifier.clickable {
                    onItemSelected(movie)
                }
            }
            MovieCard(
                modifier = clickableModifier,
                movie = movie,
            )
        }

        when {
            moviePagingItems.loadState.refresh is LoadState.Loading -> {
                item {
                    PageLoader(
                        modifier = Modifier.fillParentMaxSize(),
                    )
                }
            }

            moviePagingItems.loadState.refresh is LoadState.Error -> {
                item {
                    PageLoadingError(
                        modifier = Modifier.fillParentMaxSize(),
                        errorMessage = stringResource(R.string.error_default),
                        onClick = { moviePagingItems.retry() },
                    )
                }
            }

            moviePagingItems.loadState.append is LoadState.Loading -> {
                item {
                    NextPageLoading()
                }
            }

            moviePagingItems.loadState.append is LoadState.Error -> {
                item {
                    NextPageLoadingError(
                        errorMessage = stringResource(R.string.error_default),
                    ) {
                        moviePagingItems.retry()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenShotScreen_Preview() {
    MoviesListScreen(
        uiState = MoviesListUiState(
            imageLoader = ImageLoader.Builder(LocalContext.current).build(),
        ),
        handleEvent = {},
    )
}
