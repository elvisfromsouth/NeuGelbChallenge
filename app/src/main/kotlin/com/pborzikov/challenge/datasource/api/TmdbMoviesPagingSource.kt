package com.pborzikov.challenge.datasource.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pborzikov.challenge.data.mappers.toModel
import com.pborzikov.challenge.domian.models.MovieModel
import kotlinx.coroutines.CancellationException
import javax.inject.Inject


class TmdbMoviesPagingSource @Inject constructor(
    private val api: TheMovieDbApi,
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val currentPage = params.key ?: 1
            val movies = api.getMovies(
                page = currentPage,
            ).getOrThrow()

            LoadResult.Page(
                data = movies.results.map { it.toModel() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.results.isEmpty()) null else movies.page + 1,
            )
        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: Exception) {
            exception.printStackTrace()
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition
    }

}
