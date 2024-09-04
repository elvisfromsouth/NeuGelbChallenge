package com.pborzikov.challenge.di

import android.content.Context
import coil.ImageLoader
import com.pborzikov.challenge.BuildConfig
import com.pborzikov.challenge.datasource.api.TheMovieDbApi
import com.pborzikov.challenge.datasource.api.interceptors.TmdbPosterImageInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun recipesApi(
        okHttpClient: OkHttpClient,
    ): TheMovieDbApi {
        return TheMovieDbApi(
            baseUrl = BuildConfig.ApiBaseUrl,
            apiKey = BuildConfig.ApiKey,
            okHttpClient = okHttpClient,
        )
    }

    @Provides
    internal fun imageLoader(
        @ApplicationContext context: Context,
        interceptor: TmdbPosterImageInterceptor,
    ): ImageLoader {
        return ImageLoader(context).newBuilder()
            .components {
                add(interceptor)
            }
            .build()

    }
}
