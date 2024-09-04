package com.pborzikov.challenge.screens.moviedetails

sealed interface MovieDetailsEvent {
    object OnStart : MovieDetailsEvent
    object RetryRequest : MovieDetailsEvent
}
