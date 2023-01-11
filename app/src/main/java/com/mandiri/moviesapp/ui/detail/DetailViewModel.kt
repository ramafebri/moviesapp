package com.mandiri.moviesapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mandiri.moviesapp.common.Constants
import com.mandiri.moviesapp.domain.movies.usecase.IMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val moviesUseCase: IMoviesUseCase) : ViewModel() {
    var movieId: Int = Constants.ONE
    fun getMoviesDetail(movieId: Int) = moviesUseCase.getMoviesDetail(movieId).asLiveData()
    fun getMoviesReviews(movieId: Int, page: Int) =
        moviesUseCase.getMoviesReviews(movieId, page).asLiveData()

    fun getMoviesVideos(movieId: Int) = moviesUseCase.getMoviesVideos(movieId).asLiveData()
}