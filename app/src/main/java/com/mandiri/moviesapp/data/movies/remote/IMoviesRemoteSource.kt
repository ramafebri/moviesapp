package com.mandiri.moviesapp.data.movies.remote

import com.mandiri.moviesapp.data.base.response.ApiResponse
import com.mandiri.moviesapp.data.movies.remote.response.detail.MoviesDetailResponse
import com.mandiri.moviesapp.data.movies.remote.response.genres.MoviesGenresResponse
import com.mandiri.moviesapp.data.movies.remote.response.list.MoviesResponse
import com.mandiri.moviesapp.data.movies.remote.response.reviews.MoviesReviewsResponse
import com.mandiri.moviesapp.data.movies.remote.response.videos.MoviesVideosResponse
import kotlinx.coroutines.flow.Flow

interface IMoviesRemoteSource {
    suspend fun getMovies(page: Int, genreId: Int): Flow<ApiResponse<MoviesResponse>>
    suspend fun getMoviesDetail(movieId: Int): Flow<ApiResponse<MoviesDetailResponse>>
    suspend fun getMoviesGenres(): Flow<ApiResponse<MoviesGenresResponse>>
    suspend fun getMoviesReviews(movieId: Int, page: Int): Flow<ApiResponse<MoviesReviewsResponse>>
    suspend fun getMoviesVideos(movieId: Int): Flow<ApiResponse<MoviesVideosResponse>>
}