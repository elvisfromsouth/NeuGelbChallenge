package com.pborzikov.challenge.navigation

import kotlinx.serialization.Serializable

sealed class AppScreen {

    @Serializable
    object MoviesList : AppScreen()

    @Serializable
    class MovieDetails(
        val movieId: Int,
    ) : AppScreen()
}

