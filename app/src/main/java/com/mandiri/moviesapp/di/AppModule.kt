package com.mandiri.moviesapp.di

import com.mandiri.moviesapp.domain.movies.usecase.IMoviesUseCase
import com.mandiri.moviesapp.domain.movies.usecase.MoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideMoviesUseCase(useCase: MoviesUseCase): IMoviesUseCase
}