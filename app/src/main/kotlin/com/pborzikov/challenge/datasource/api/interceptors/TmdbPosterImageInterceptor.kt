package com.pborzikov.challenge.datasource.api.interceptors

import coil.intercept.Interceptor
import coil.request.ImageResult
import com.pborzikov.challenge.data.repositories.TmdbImageUrlRepository
import timber.log.Timber
import javax.inject.Inject

class TmdbPosterImageInterceptor @Inject constructor(
    private val configurationManager: TmdbImageUrlRepository,
) : Interceptor {

    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val newUrl = configurationManager.getPosterUrl(
            imageUrl = chain.request.data.toString(),
            viewPortSize = chain.size,
        )
        Timber.d("Request image url: ${newUrl}")

        return chain.proceed(
            chain.request.newBuilder()
                .data(newUrl)
                .build(),
        )
    }
}


