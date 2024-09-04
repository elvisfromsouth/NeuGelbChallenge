package com.pborzikov.challenge.screens.moviedetails.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pborzikov.challenge.navigation.AppScreen
import com.pborzikov.challenge.screens.LocalCoilImageLoader
import com.pborzikov.challenge.screens.moviedetails.MovieDetailsViewModel


fun NavGraphBuilder.movieDetailsScreen() {
    composable<AppScreen.MovieDetails> { navBackStackEntry ->
        val arguments = navBackStackEntry.toRoute<AppScreen.MovieDetails>()
        MovieDetailsRoute(
            movieId = arguments.movieId,
        )
    }
}

@Composable
internal fun MovieDetailsRoute(
    movieId: Int,
) {
    val viewModel: MovieDetailsViewModel = hiltViewModel<MovieDetailsViewModel, MovieDetailsViewModel.Factory>(
        creationCallback = { factory -> factory.create(movieId = movieId) },
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CompositionLocalProvider(
        LocalCoilImageLoader provides uiState.imageLoader,
    ) {
        MovieDetailsScreen(
            uiState = uiState,
            handleEvent = viewModel::handleEvent,
        )
    }
}
