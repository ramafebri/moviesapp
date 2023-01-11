package com.mandiri.moviesapp.data.movies

import com.mandiri.moviesapp.data.base.response.ApiResponse
import com.mandiri.moviesapp.data.movies.remote.MoviesRemoteSource
import com.mandiri.moviesapp.domain.movies.Resource
import com.mandiri.moviesapp.domain.movies.model.detail.MoviesDetailModel
import com.mandiri.moviesapp.domain.movies.model.genres.MoviesGenresItemModel
import com.mandiri.moviesapp.domain.movies.model.list.MoviesModel
import com.mandiri.moviesapp.domain.movies.model.reviews.MoviesReviewsModel
import com.mandiri.moviesapp.domain.movies.model.videos.MoviesVideosItemModel
import com.mandiri.moviesapp.domain.movies.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val remoteDataSource: MoviesRemoteSource) :
    IMoviesRepository {
    override fun getMoviesList(page: Int, genreId: Int): Flow<Resource<MoviesModel>> {
        return flow {
            emit(Resource.Loading())
            when (val response = remoteDataSource.getMovies(page, genreId).single()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(MoviesModel.from(response.data)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(response.throwable.message.orEmpty()))
                }
            }
        }
    }

    override fun getMoviesDetail(movieId: Int): Flow<Resource<MoviesDetailModel>> {
        return flow {
            emit(Resource.Loading())
            when (val response = remoteDataSource.getMoviesDetail(movieId).single()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(MoviesDetailModel.from(response.data)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(response.throwable.message.orEmpty()))
                }
            }
        }
    }

    override fun getMoviesGenres(): Flow<Resource<List<MoviesGenresItemModel>>> {
        return flow {
            emit(Resource.Loading())
            when (val response = remoteDataSource.getMoviesGenres().single()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(MoviesGenresItemModel.from(response.data.genres)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(response.throwable.message.orEmpty()))
                }
            }
        }
    }

    override fun getMoviesReviews(movieId: Int, page: Int): Flow<Resource<MoviesReviewsModel>> {
        return flow {
            emit(Resource.Loading())
            when (val response = remoteDataSource.getMoviesReviews(movieId, page).single()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(MoviesReviewsModel.from(response.data)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(response.throwable.message.orEmpty()))
                }
            }
        }
    }

    override fun getMoviesVideos(movieId: Int): Flow<Resource<List<MoviesVideosItemModel>>> {
        return flow {
            emit(Resource.Loading())
            when (val response = remoteDataSource.getMoviesVideos(movieId).single()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(MoviesVideosItemModel.from(response.data.results)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(response.throwable.message.orEmpty()))
                }
            }
        }
    }
}