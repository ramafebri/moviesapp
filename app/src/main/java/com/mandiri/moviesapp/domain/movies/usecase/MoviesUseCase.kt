package com.mandiri.moviesapp.domain.movies.usecase

import com.mandiri.moviesapp.domain.movies.Resource
import com.mandiri.moviesapp.domain.movies.model.detail.MoviesDetailModel
import com.mandiri.moviesapp.domain.movies.model.genres.MoviesGenresItemModel
import com.mandiri.moviesapp.domain.movies.model.list.MoviesModel
import com.mandiri.moviesapp.domain.movies.model.reviews.MoviesReviewsModel
import com.mandiri.moviesapp.domain.movies.model.videos.MoviesVideosItemModel
import com.mandiri.moviesapp.domain.movies.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesUseCase @Inject constructor(private val repository: IMoviesRepository) :
    IMoviesUseCase {
    override fun getMoviesList(page: Int, genreId: Int): Flow<Resource<MoviesModel>> {
        return repository.getMoviesList(page, genreId)
    }

    override fun getMoviesDetail(movieId: Int): Flow<Resource<MoviesDetailModel>> {
        return repository.getMoviesDetail(movieId)
    }

    override fun getMoviesGenres(): Flow<Resource<List<MoviesGenresItemModel>>> {
        return repository.getMoviesGenres()
    }

    override fun getMoviesReviews(movieId: Int, page: Int): Flow<Resource<MoviesReviewsModel>> {
        return repository.getMoviesReviews(movieId, page)
    }

    override fun getMoviesVideos(movieId: Int): Flow<Resource<List<MoviesVideosItemModel>>> {
        return repository.getMoviesVideos(movieId)
    }
}