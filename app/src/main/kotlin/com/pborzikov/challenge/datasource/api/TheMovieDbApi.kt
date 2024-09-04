package com.pborzikov.challenge.datasource.api

import com.pborzikov.challenge.datasource.api.dto.ConfigurationResponseDto
import com.pborzikov.challenge.datasource.api.dto.MovieDetailsDto
import com.pborzikov.challenge.datasource.api.dto.MovieDto
import com.pborzikov.challenge.datasource.api.dto.PageResponseDto
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {

    // https://developer.themoviedb.org/reference/discover-movie
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int? = null,
        @Query("sort_by") sort: String = "primary_release_date.desc",
        @Query("language") language: String = "en-US",
    ): Result<PageResponseDto<MovieDto>>

    // https://developer.themoviedb.org/reference/movie-details
    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
    ): Result<MovieDetailsDto>

    // https://developer.themoviedb.org/reference/search-movie
    @GET("search/movie")
    suspend fun search(
        @Query("query") query: String,
    ): Result<PageResponseDto<MovieDto>>

    // https://developer.themoviedb.org/reference/configuration-details
    @GET("configuration")
    suspend fun getConfiguration(): Result<ConfigurationResponseDto>
}

fun TheMovieDbApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json { ignoreUnknownKeys = true },
): TheMovieDbApi {
    val retrofit = retrofit(baseUrl, apiKey, okHttpClient, json)
    return retrofit.create()
}

