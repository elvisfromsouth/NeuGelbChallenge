package com.pborzikov.challenge.datasource.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ConfigurationResponseDto(
    @SerialName("images") val images: ImageConfigurationResponseDto,
)


