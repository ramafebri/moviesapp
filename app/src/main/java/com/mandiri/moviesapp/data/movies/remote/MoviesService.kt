package com.mandiri.moviesapp.data.movies.remote

import com.mandiri.moviesapp.common.ApiConstants
import com.mandiri.moviesapp.data.movies.remote.response.*
import com.mandiri.moviesapp.data.movies.remote.response.detail.MoviesDetailResponse
import com.mandiri.moviesapp.data.movies.remote.response.genres.MoviesGenresResponse
import com.mandiri.moviesapp.data.movies.remote.response.list.MoviesResponse
import com.mandiri.moviesapp.data.movies.remote.response.reviews.MoviesReviewsResponse
import com.mandiri.moviesapp.data.movies.remote.response.videos.MoviesVideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET(ApiConstants.GET_MOVIES_GENRES_URL)
    suspend fun getMoviesGenres(): Response<MoviesGenresResponse>

    @GET(ApiConstants.GET_MOVIES_URL)
    suspend fun getMoviesList(
        @Query(ApiConstants.PAGE) page: Int,
        @Query(ApiConstants.WITH_GENRES) genreId: Int
    ): Response<MoviesResponse>

    @GET(ApiConstants.GET_MOVIES_DETAIL_URL)
    suspend fun getMoviesDetail(
        @Path(ApiConstants.MOVIE_ID) movieId: Int
    ): Response<MoviesDetailResponse>

    @GET(ApiConstants.GET_MOVIES_VIDEOS_URL)
    suspend fun getMoviesVideos(
        @Path(ApiConstants.MOVIE_ID) movieId: Int
    ): Response<MoviesVideosResponse>

    @GET(ApiConstants.GET_MOVIES_REVIEWS_URL)
    suspend fun getMoviesReviews(
        @Path(ApiConstants.MOVIE_ID) movieId: Int,
        @Query(ApiConstants.PAGE) page: Int
    ): Response<MoviesReviewsResponse>
}