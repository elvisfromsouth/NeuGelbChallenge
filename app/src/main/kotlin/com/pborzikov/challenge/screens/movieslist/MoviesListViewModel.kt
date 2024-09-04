package com.pborzikov.challenge.screens.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import coil.ImageLoader
import com.pborzikov.challenge.data.mappers.toSuggestionModel
import com.pborzikov.challenge.domian.MoviesRepository
import com.pborzikov.challenge.domian.models.MovieModel
import com.pborzikov.challenge.navigation.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repository: MoviesRepository,
    imageLoader: ImageLoader,
) : ViewModel() {

    private val _moviesPagingDataFlow: MutableStateFlow<PagingData<MovieModel>> = MutableStateFlow(
        value = PagingData.empty(),
    )

    private val _uiState = MutableStateFlow(
        MoviesListUiState(
            imageLoader = imageLoader,
            moviesPaging = _moviesPagingDataFlow.asStateFlow(),
        ),
    )
    val uiState = _uiState.asStateFlow()

    private val movieSearchQueryFlow = MutableStateFlow(_uiState.value.movieSearchQuery)

    private fun initRequests() {
        observeMoviesPagination()
        observeMovieSearch()
    }

    private fun observeMoviesPagination() {
        viewModelScope.launch {
            repository.getMovies()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect { paging ->
                    _moviesPagingDataFlow.value = paging
                }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeMovieSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            movieSearchQueryFlow
                .debounce(timeoutMillis = SEARCH_REQUEST_DEBOUNCE)
                .map { query ->
                    if (query.length < MIN_SEARCH_LENGTH) {
                        Result.success(emptyList())
                    } else {
                        repository.findMovies(query)
                            .map { moviesList ->
                                moviesList
                                    .take(MAX_MOVIE_SUGGESTIONS_COUNT)
                                    .map { movie ->
                                        movie.toSuggestionModel()
                                    }
                            }
                    }
                }
                .collectLatest { resultList ->
                    resultList
                        .onSuccess { list ->
                            _uiState.update {
                                it.copy(
                                    movieSearchResult = list,
                                )
                            }
                        }.onFailure { error ->
                            Timber.e(error)
                        }
                }
        }
    }

    fun handleEvent(event: MoviesListEvent) {
        when (event) {
            MoviesListEvent.OnStart -> initRequests()

            MoviesListEvent.OnNavigated -> _uiState.update {
                it.copy(navigation = null)
            }

            is MoviesListEvent.OnMovieClicked -> _uiState.update {
                it.copy(
                    navigation = AppScreen.MovieDetails(movieId = event.movieId),
                )
            }

            is MoviesListEvent.OnSearch -> _uiState.update {
                movieSearchQueryFlow.update { event.request }
                it.copy(
                    movieSearchQuery = event.request,
                )
            }

            is MoviesListEvent.OnSuggestClicked -> _uiState.update {
                it.copy(
                    movieSearchQuery = "",
                    movieSearchResult = emptyList(),
                    navigation = AppScreen.MovieDetails(movieId = event.movieId),
                )
            }
        }
    }

    companion object {
        private const val MAX_MOVIE_SUGGESTIONS_COUNT = 4
        private const val MIN_SEARCH_LENGTH = 3
        private const val SEARCH_REQUEST_DEBOUNCE = 500L
    }
}
