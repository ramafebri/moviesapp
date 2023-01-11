package com.mandiri.moviesapp.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mandiri.moviesapp.common.Constants
import com.mandiri.moviesapp.domain.movies.usecase.IMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(private val moviesUseCase: IMoviesUseCase) : ViewModel() {
    var movieId: Int = Constants.ONE
    var currentPage: Int = Constants.ONE
    var totalPages: Int = Constants.ONE
    fun getMoviesReviews(movieId: Int, page: Int) =
        moviesUseCase.getMoviesReviews(movieId, page).asLiveData()
}