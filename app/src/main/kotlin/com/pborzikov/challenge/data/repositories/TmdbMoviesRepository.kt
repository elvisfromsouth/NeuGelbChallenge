package com.pborzikov.challenge.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pborzikov.challenge.datasource.api.TheMovieDbApi
import com.pborzikov.challenge.datasource.api.TmdbMoviesPagingSource
import com.pborzikov.challenge.datasource.api.dto.MovieDetailsDto
import com.pborzikov.challenge.datasource.api.dto.MovieDto
import com.pborzikov.challenge.domian.MoviesRepository
import com.pborzikov.challenge.domian.models.MovieModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TmdbMoviesRepository @Inject constructor(
    private val api: TheMovieDbApi,
    private val pagingSource: TmdbMoviesPagingSource,
) : MoviesRepository {

    override suspend fun getMovies(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = {
                pagingSource
            },
        ).flow
    }

    override suspend fun findMovies(query: String): Result<List<MovieDto>> {
        return api.search(query)
            .map { it.results }
    }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDto> {
        return api.getMovie(movieId = movieId)
    }
}
