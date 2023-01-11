package com.mandiri.moviesapp.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.mandiri.moviesapp.R
import com.mandiri.moviesapp.common.ApiConstants
import com.mandiri.moviesapp.common.Constants
import com.mandiri.moviesapp.common.Constants.ZERO
import com.mandiri.moviesapp.common.DateHelper
import com.mandiri.moviesapp.databinding.ActivityDetailBinding
import com.mandiri.moviesapp.domain.movies.Resource
import com.mandiri.moviesapp.domain.movies.model.detail.MoviesDetailModel
import com.mandiri.moviesapp.domain.movies.model.genres.MoviesGenresItemModel
import com.mandiri.moviesapp.domain.movies.model.reviews.MoviesReviewsItemModel
import com.mandiri.moviesapp.domain.movies.model.videos.MoviesVideosItemModel
import com.mandiri.moviesapp.extension.*
import com.mandiri.moviesapp.ui.base.BaseActivity
import com.mandiri.moviesapp.ui.review.ReviewActivity
import com.mandiri.moviesapp.ui.video.VideoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    private val viewModel: DetailViewModel by viewModels()
    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBackButton()
        initData()
        initListener()
    }

    private fun initData() {
        viewModel.movieId = intent.getIntExtra(EXTRA_MOVIE_ID, ZERO)
        getMoviesDetail(viewModel.movieId)
        getMoviesReviews(viewModel.movieId)
        getMoviesVideos(viewModel.movieId)
    }

    private fun initListener() {
        binding.textViewSeeAllReview.setOnClickListener {
            ReviewActivity.startActivity(this, viewModel.movieId)
        }
    }

    private fun getMoviesDetail(movieId: Int) {
        viewModel.getMoviesDetail(movieId).observe(this) { res ->
            when (res) {
                is Resource.Success -> {
                    binding.progressBar.setGone()
                    if (res.data != null) {
                        setView(res.data)
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.setGone()
                    showToast(res.message.orEmpty())
                }
                is Resource.Loading -> {
                    binding.progressBar.setVisible()
                }
            }
        }
    }

    private fun getMoviesReviews(movieId: Int) {
        viewModel.getMoviesReviews(movieId, ApiConstants.DEFAULT_PAGE).observe(this) { res ->
            when (res) {
                is Resource.Success -> {
                    if (res.data != null) {
                        setReviewCard(res.data.results.firstOrNull())
                    } else hideReview()
                }
                is Resource.Error -> {
                    hideReview()
                    showToast(res.message.orEmpty())
                }
                is Resource.Loading -> {
                    // Do nothing
                }
            }
        }
    }

    private fun getMoviesVideos(movieId: Int) {
        viewModel.getMoviesVideos(movieId).observe(this) { res ->
            when (res) {
                is Resource.Success -> {
                    if (res.data != null) {
                        setYoutubeView(res.data)
                    } else hideVideo()
                }
                is Resource.Error -> {
                    hideVideo()
                    showToast(res.message.orEmpty())
                }
                is Resource.Loading -> {
                    // Do nothing
                }
            }
        }
    }

    private fun setView(data: MoviesDetailModel) {
        with(binding) {
            textViewTitle.text = data.title
            textViewDate.text = data.releaseDate
            textViewRating.text = getString(R.string.rating_text, data.voteAverage.toString())
            textViewDuration.text = getString(R.string.duration_text, data.runtime.toString())
            val genres = generateGenre(data.genres)
            textViewGenre.text = getString(R.string.genres_text, genres)
            textViewOverview.text = data.overview
            with(imageViewMovies) {
                Glide.with(context)
                    .load(getMoviesUrl(data.posterPath))
                    .into(this)
            }
        }
    }

    private fun generateGenre(genresList: List<MoviesGenresItemModel>): String {
        var genresString = Constants.EMPTY
        genresList.forEachIndexed { index, item ->
            genresString += item.name
            if (index < genresList.lastIndex) {
                genresString += Constants.COMMA_WITH_SPACE
            }
        }
        return genresString
    }

    private fun setReviewCard(data: MoviesReviewsItemModel?) {
        if (data == null) {
            hideReview()
            return
        }
        with(binding.itemReview) {
            textViewName.text = data.authorDetails.username
            textViewDate.text =
                DateHelper.dateParseToString(data.updatedAt, DateHelper.DATE_TIME_FORMAT_DISPLAY)
            textViewReview.text = data.content
            with(imageViewPhoto) {
                Glide.with(context)
                    .load(getMoviesUrl(data.authorDetails.avatarPath))
                    .into(this)
            }
        }
    }

    private fun hideReview() {
        with(binding) {
            textViewEmptyReview.setVisible()
            itemReview.root.setGone()
            textViewSeeAllReview.setGone()
        }
    }

    private fun setYoutubeView(data: List<MoviesVideosItemModel>) {
        if (data.isEmpty()) {
            hideVideo()
            return
        }
        with(binding.itemVideo) {
            imageViewPlay.setOnClickListener {
                VideoActivity.startActivity(this@DetailActivity, data)
            }
            with(youtubeThumbnailView) {
                Glide.with(context)
                    .load(getYoutubeThumbnailUrl(data.first().key))
                    .into(this)
            }
        }
    }

    private fun hideVideo() {
        with(binding) {
            textViewEmptyVideo.setVisible()
            itemVideo.root.setGone()
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun startActivity(activity: Activity, movieId: Int) {
            activity.startActivity(Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
            })
        }
    }
}