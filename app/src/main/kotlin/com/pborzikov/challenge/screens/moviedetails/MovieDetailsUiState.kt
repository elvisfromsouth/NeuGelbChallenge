package com.pborzikov.challenge.screens.moviedetails

import androidx.compose.runtime.Stable
import coil.ImageLoader
import com.pborzikov.challenge.screens.moviedetails.models.MovieDetailAppearanceModel

@Stable
data class MovieDetailsUiState(
    val imageLoader: ImageLoader,

    val isLoading: Boolean = true,
    val errorMessage: String? = null,

    val title: String = "",
    val overview: String = "",
    val posterPath: String? = null,
    val movieDetails: List<MovieDetailAppearanceModel> = emptyList(),
)
