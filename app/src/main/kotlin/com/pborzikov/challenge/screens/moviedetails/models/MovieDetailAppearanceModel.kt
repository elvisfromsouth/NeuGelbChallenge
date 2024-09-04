package com.pborzikov.challenge.screens.moviedetails.models

import androidx.annotation.StringRes

data class MovieDetailAppearanceModel(
    @StringRes
    val title: Int,
    val description: String,
)
