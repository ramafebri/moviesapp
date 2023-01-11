package com.mandiri.moviesapp.domain.movies.repository

import com.mandiri.moviesapp.domain.movies.Resource
import com.mandiri.moviesapp.domain.movies.model.detail.MoviesDetailModel
import com.mandiri.moviesapp.domain.movies.model.genres.MoviesGenresItemModel
import com.mandiri.moviesapp.domain.movies.model.list.MoviesModel
import com.mandiri.moviesapp.domain.movies.model.reviews.MoviesReviewsModel
import com.mandiri.moviesapp.domain.movies.model.videos.MoviesVideosItemModel
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    fun getMoviesList(
        page: Int,
        genreId: Int
    ): Flow<Resource<MoviesModel>>
    fun getMoviesDetail(movieId: Int): Flow<Resource<MoviesDetailModel>>
    fun getMoviesGenres(): Flow<Resource<List<MoviesGenresItemModel>>>
    fun getMoviesReviews(movieId: Int, page: Int): Flow<Resource<MoviesReviewsModel>>
    fun getMoviesVideos(movieId: Int): Flow<Resource<List<MoviesVideosItemModel>>>
}