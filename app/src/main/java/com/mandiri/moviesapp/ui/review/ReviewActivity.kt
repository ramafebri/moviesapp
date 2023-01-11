package com.mandiri.moviesapp.ui.review

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.mandiri.moviesapp.common.ApiConstants
import com.mandiri.moviesapp.common.Constants.ZERO
import com.mandiri.moviesapp.databinding.ActivityReviewBinding
import com.mandiri.moviesapp.domain.movies.Resource
import com.mandiri.moviesapp.extension.showToast
import com.mandiri.moviesapp.ui.base.BaseActivity
import com.mandiri.moviesapp.ui.detail.DetailActivity.Companion.EXTRA_MOVIE_ID
import com.mandiri.moviesapp.ui.review.adapter.ReviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewActivity : BaseActivity<ActivityReviewBinding>(), ReviewAdapter.AdapterListener {
    private val viewModel: ReviewViewModel by viewModels()
    private val reviewAdapter by lazy {
        ReviewAdapter(this)
    }

    override fun getViewBinding(): ActivityReviewBinding {
        return ActivityReviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBackButton()
        viewModel.movieId = intent.getIntExtra(EXTRA_MOVIE_ID, ZERO)
        initRecyclerView()
        getMoviesReviews(
            viewModel.movieId,
            ApiConstants.DEFAULT_PAGE
        )
    }

    private fun initRecyclerView() {
        binding.rvReview.adapter = reviewAdapter
    }

    private fun getMoviesReviews(movieId: Int, page: Int) {
        viewModel.currentPage = page
        viewModel.getMoviesReviews(movieId, page).observe(this) { res ->
            when (res) {
                is Resource.Success -> {
                    if (res.data != null) {
                        viewModel.totalPages = res.data.totalPages
                        reviewAdapter.setIsTheLastPage(viewModel.currentPage == viewModel.totalPages)
                        reviewAdapter.setDifferData(res.data.results)
                    }
                }
                is Resource.Error -> {
                    showToast(res.message.orEmpty())
                }
                is Resource.Loading -> {
                    // Do nothing
                }
            }
        }
    }

    override fun loadNextPage() {
        getMoviesReviews(viewModel.movieId, viewModel.currentPage + 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvReview.adapter = null
        reviewAdapter.releaseReference()
    }

    companion object {
        fun startActivity(activity: Activity, movieId: Int) {
            activity.startActivity(Intent(activity, ReviewActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
            })
        }
    }
}