package com.pborzikov.challenge.datasource.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("name") val name: String? = null,
)
