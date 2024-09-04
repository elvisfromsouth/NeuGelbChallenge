package com.pborzikov.challenge.data.mappers

import com.pborzikov.challenge.datasource.api.dto.MovieDto
import com.pborzikov.challenge.domian.models.MovieModel
import com.pborzikov.challenge.domian.models.SuggestionMovieModel


fun MovieDto.toModel(): MovieModel {
    return MovieModel(
        id = id,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage.toString(),
        previewUrl = posterPath,
    )
}

fun MovieDto.toSuggestionModel(): SuggestionMovieModel {
    return SuggestionMovieModel(
        id = id,
        title = "$title [$releaseDate]",
    )
}
