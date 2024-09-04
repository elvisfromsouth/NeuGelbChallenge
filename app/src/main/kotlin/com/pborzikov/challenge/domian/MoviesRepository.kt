package com.pborzikov.challenge.domian

import androidx.paging.PagingData
import com.pborzikov.challenge.datasource.api.dto.MovieDetailsDto
import com.pborzikov.challenge.datasource.api.dto.MovieDto
import com.pborzikov.challenge.domian.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): Flow<PagingData<MovieModel>>
    suspend fun findMovies(query: String): Result<List<MovieDto>>
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDto>
}
