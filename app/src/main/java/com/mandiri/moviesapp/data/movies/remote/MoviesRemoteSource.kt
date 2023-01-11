package com.mandiri.moviesapp.data.movies.remote

import com.mandiri.moviesapp.data.base.common.BaseServiceAPI
import com.mandiri.moviesapp.data.base.response.ApiResponse
import com.mandiri.moviesapp.data.movies.remote.response.detail.MoviesDetailResponse
import com.mandiri.moviesapp.data.movies.remote.response.genres.MoviesGenresResponse
import com.mandiri.moviesapp.data.movies.remote.response.list.MoviesResponse
import com.mandiri.moviesapp.data.movies.remote.response.reviews.MoviesReviewsResponse
import com.mandiri.moviesapp.data.movies.remote.response.videos.MoviesVideosResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRemoteSource @Inject constructor(private val apiService: MoviesService) :
    BaseServiceAPI(),
    IMoviesRemoteSource {
    override suspend fun getMovies(page: Int, genreId: Int): Flow<ApiResponse<MoviesResponse>> {
        return callApi {
            apiService.getMoviesList(
                page = page,
                genreId = genreId
            )
        }
    }

    override suspend fun getMoviesDetail(movieId: Int): Flow<ApiResponse<MoviesDetailResponse>> {
        return callApi {
            apiService.getMoviesDetail(
                movieId = movieId
            )
        }
    }

    override suspend fun getMoviesGenres(): Flow<ApiResponse<MoviesGenresResponse>> {
        return callApi {
            apiService.getMoviesGenres()
        }
    }

    override suspend fun getMoviesReviews(movieId: Int, page: Int): Flow<ApiResponse<MoviesReviewsResponse>> {
        return callApi {
            apiService.getMoviesReviews(
                movieId = movieId,
                page
            )
        }
    }

    override suspend fun getMoviesVideos(movieId: Int): Flow<ApiResponse<MoviesVideosResponse>> {
        return callApi {
            apiService.getMoviesVideos(
                movieId = movieId
            )
        }
    }
}