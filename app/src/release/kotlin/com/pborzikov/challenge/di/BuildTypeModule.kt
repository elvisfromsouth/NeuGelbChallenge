package com.pborzikov.challenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BuildTypeModule {

    @Provides
    @Singleton
    internal fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}