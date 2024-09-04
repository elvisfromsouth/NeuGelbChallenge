package com.pborzikov.challenge.screens.movieslist

sealed interface MoviesListEvent {
    object OnStart : MoviesListEvent
    object OnNavigated : MoviesListEvent
    class OnMovieClicked(val movieId: Int) : MoviesListEvent
    class OnSuggestClicked(val movieId: Int) : MoviesListEvent
    class OnSearch(val request: String) : MoviesListEvent
}
