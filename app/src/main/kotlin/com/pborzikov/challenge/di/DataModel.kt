package com.pborzikov.challenge.di

import com.pborzikov.challenge.data.repositories.TmdbMoviesRepository
import com.pborzikov.challenge.domian.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModel {

    @Singleton
    @Provides
    fun provideRepository(
        impl: TmdbMoviesRepository,
    ): MoviesRepository =
        impl
}

