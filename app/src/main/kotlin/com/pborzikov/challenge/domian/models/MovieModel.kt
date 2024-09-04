package com.pborzikov.challenge.domian.models

data class MovieModel(
    val id: Int,
    val title: String,
    val previewUrl: String?,
    val releaseDate: String,
    val voteAverage: String,
)
