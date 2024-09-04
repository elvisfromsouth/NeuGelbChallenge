package com.pborzikov.challenge.screens.movieslist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pborzikov.challenge.navigation.AppScreen
import com.pborzikov.challenge.screens.LocalCoilImageLoader
import com.pborzikov.challenge.screens.LocalNavController
import com.pborzikov.challenge.screens.movieslist.MoviesListEvent
import com.pborzikov.challenge.screens.movieslist.MoviesListViewModel


fun NavGraphBuilder.moviesListScreen() {
    composable<AppScreen.MoviesList> {
        MoviesListRoute()
    }
}


@Composable
fun MoviesListRoute(
    viewModel: MoviesListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val navController = LocalNavController.current
    uiState.navigation?.let { screen ->
        viewModel.handleEvent(MoviesListEvent.OnNavigated)
        navController.navigate(screen)
    }

    CompositionLocalProvider(
        LocalCoilImageLoader provides uiState.imageLoader,
    ) {
        MoviesListScreen(
            uiState = uiState,
            handleEvent = remember { viewModel::handleEvent },
        )
    }
}
