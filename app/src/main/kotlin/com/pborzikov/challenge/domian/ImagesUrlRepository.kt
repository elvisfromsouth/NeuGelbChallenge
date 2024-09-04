package com.pborzikov.challenge.domian

import coil.size.Size

interface ImagesUrlRepository {
    suspend fun getPosterUrl(imageUrl: String, viewPortSize: Size): String
}
