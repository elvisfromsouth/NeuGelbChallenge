package com.pborzikov.challenge.data.repositories

import coil.size.Size
import com.pborzikov.challenge.data.ImageUrlFactory
import com.pborzikov.challenge.datasource.api.TheMovieDbApi
import com.pborzikov.challenge.domian.ImagesUrlRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject


class TmdbImageUrlRepository @Inject constructor(
    private val api: TheMovieDbApi,
) : ImagesUrlRepository {

    private val mutex = Mutex()

    @Volatile
    private var postersUrlFactory: ImageUrlFactory? = null

    override suspend fun getPosterUrl(
        imageUrl: String,
        viewPortSize: Size,
    ): String {
        return getPostersUrlFactory().getImageUrl(imageUrl, viewPortSize)
    }

    private suspend fun getPostersUrlFactory(): ImageUrlFactory {
        mutex.withLock {
            val posters = postersUrlFactory ?: let {
                val imageConfiguration = api.getConfiguration().getOrThrow().images
                ImageUrlFactory(
                    baseUrl = imageConfiguration.secureBaseUrl,
                    sizes = imageConfiguration.posterSizes,
                )
            }
            postersUrlFactory = posters
            return posters
        }
    }

}
