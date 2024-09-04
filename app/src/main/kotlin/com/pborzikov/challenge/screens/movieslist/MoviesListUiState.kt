package com.pborzikov.challenge.screens.movieslist

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import coil.ImageLoader
import com.pborzikov.challenge.domian.models.MovieModel
import com.pborzikov.challenge.domian.models.SuggestionMovieModel
import com.pborzikov.challenge.navigation.AppScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class MoviesListUiState(
    val imageLoader: ImageLoader,
    val navigation: AppScreen? = null,

    val movieSearchQuery: String = "",
    val movieSearchResult: List<SuggestionMovieModel> = emptyList(),

    val moviesPaging: Flow<PagingData<MovieModel>> = emptyFlow(),
)
