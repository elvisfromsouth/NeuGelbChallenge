package com.pborzikov.challenge.screens.moviedetails.mappers

import com.pborzikov.challenge.R
import com.pborzikov.challenge.datasource.api.dto.MovieDetailsDto
import com.pborzikov.challenge.screens.moviedetails.models.MovieDetailAppearanceModel


fun MovieDetailsDto.getDetailsList(): List<MovieDetailAppearanceModel> {
    return listOf(
        MovieDetailAppearanceModel(R.string.genre, genres.map { genre -> genre.name }.joinToString()),
        MovieDetailAppearanceModel(R.string.country, originCountry.joinToString()),
        MovieDetailAppearanceModel(R.string.release_date, releaseDate),
        MovieDetailAppearanceModel(R.string.vote, voteAverage.toString()),
        MovieDetailAppearanceModel(R.string.runtime, runtime.toString()),
    )
}
