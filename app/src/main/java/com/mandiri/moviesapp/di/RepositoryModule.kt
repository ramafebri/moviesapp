package com.mandiri.moviesapp.di

import com.mandiri.moviesapp.data.movies.MoviesRepository
import com.mandiri.moviesapp.domain.movies.repository.IMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMoviesRepository(repository: MoviesRepository): IMoviesRepository
}