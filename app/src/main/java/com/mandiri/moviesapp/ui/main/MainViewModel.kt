package com.mandiri.moviesapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mandiri.moviesapp.common.Constants.ONE
import com.mandiri.moviesapp.domain.movies.model.genres.MoviesGenresItemModel
import com.mandiri.moviesapp.domain.movies.usecase.IMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val moviesUseCase: IMoviesUseCase) : ViewModel() {
    var genresList: List<MoviesGenresItemModel> = emptyList()
    var selectedGenre: MoviesGenresItemModel? = null
    var currentPage: Int = ONE
    var totalPages: Int = ONE

    fun getMoviesGenres() = moviesUseCase.getMoviesGenres().asLiveData()
    fun getMoviesList(
        page: Int,
        genreId: Int
    ) = moviesUseCase.getMoviesList(page, genreId).asLiveData()
}