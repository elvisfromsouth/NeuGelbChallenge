package com.pborzikov.challenge.screens.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.pborzikov.challenge.domian.MoviesRepository
import com.pborzikov.challenge.screens.moviedetails.mappers.getDetailsList
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber


@HiltViewModel(assistedFactory = MovieDetailsViewModel.Factory::class)
class MovieDetailsViewModel @AssistedInject constructor(
    @Assisted private val movieId: Int,
    private val repository: MoviesRepository,
    imageLoader: ImageLoader,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        value = MovieDetailsUiState(
            imageLoader = imageLoader,
        ),
    )
    val uiState = _uiState.asStateFlow()

    private fun initRequests() {
        requestMovieDetails()
    }

    private fun requestMovieDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            repository.getMovieDetails(movieId)
                .onSuccess { movieDto ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            posterPath = movieDto.posterPath,
                            movieDetails = movieDto.getDetailsList(),
                        )
                    }
                }
                .onFailure { error ->
                    Timber.e(error)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message,
                        )
                    }
                }
        }
    }

    fun handleEvent(event: MovieDetailsEvent) {
        when (event) {
            MovieDetailsEvent.OnStart -> initRequests()
            MovieDetailsEvent.RetryRequest -> requestMovieDetails()
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(movieId: Int): MovieDetailsViewModel
    }
}
