package com.pborzikov.challenge.datasource.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieDetailsDto(
    @SerialName("id") val id: Int,
    @SerialName("genres") val genres: List<GenreDto>,
    @SerialName("origin_country") val originCountry: List<String>,
    @SerialName("title") val title: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("overview") val overview: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("runtime") val runtime: Int? = null,
    @SerialName("status") val status: String? = null,
)

