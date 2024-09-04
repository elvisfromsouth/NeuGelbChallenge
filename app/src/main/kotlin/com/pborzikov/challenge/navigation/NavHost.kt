package com.pborzikov.challenge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pborzikov.challenge.screens.LocalNavController
import com.pborzikov.challenge.screens.moviedetails.ui.movieDetailsScreen
import com.pborzikov.challenge.screens.movieslist.ui.moviesListScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: Any = AppScreen.MoviesList,
) {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavController provides navController,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            moviesListScreen()
            movieDetailsScreen()
        }
    }
}


