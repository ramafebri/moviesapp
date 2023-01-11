package com.mandiri.moviesapp.ui.main

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import com.mandiri.moviesapp.common.ApiConstants
import com.mandiri.moviesapp.databinding.ActivityMainBinding
import com.mandiri.moviesapp.domain.movies.Resource
import com.mandiri.moviesapp.domain.movies.model.genres.MoviesGenresItemModel
import com.mandiri.moviesapp.extension.orZero
import com.mandiri.moviesapp.extension.setGone
import com.mandiri.moviesapp.extension.setVisible
import com.mandiri.moviesapp.extension.showToast
import com.mandiri.moviesapp.ui.base.BaseActivity
import com.mandiri.moviesapp.ui.detail.DetailActivity
import com.mandiri.moviesapp.ui.main.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), MoviesAdapter.AdapterListener {
    private val viewModel: MainViewModel by viewModels()
    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(this)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMoviesGenres()
        initRecyclerView()
        initViewListener()
        initGenreBottomSheetObserver()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        getMoviesList(ApiConstants.DEFAULT_PAGE, viewModel.selectedGenre?.id.orZero())
    }

    private fun initRecyclerView() {
        binding.rvMovies.adapter = moviesAdapter
    }

    private fun initViewListener() {
        binding.buttonFilter.setOnClickListener {
            showGenreBottomSheet()
        }
    }

    private fun showGenreBottomSheet() {
        GenresBottomSheetFragment.newInstance(
            genresList = viewModel.genresList,
            selectedGenre = viewModel.selectedGenre?.id.orZero()
        ).show(supportFragmentManager, GenresBottomSheetFragment.TAG)
    }

    private fun initGenreBottomSheetObserver() {
        supportFragmentManager.setFragmentResultListener(
            GenresBottomSheetFragment.REQ_GENRE_KEY,
            this
        ) { _, bundle ->
            val genre = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(
                    GenresBottomSheetFragment.EXTRA_GENRES_DATA,
                    MoviesGenresItemModel::class.java
                )
            } else {
                bundle.getParcelable(GenresBottomSheetFragment.EXTRA_GENRES_DATA)
            }
            onGenreSelected(genre)
        }
    }

    private fun onGenreSelected(genre: MoviesGenresItemModel?) {
        if (genre == null || genre.id == viewModel.selectedGenre?.id) return
        viewModel.selectedGenre = genre
        binding.textInputEditTextGenres.setText(genre.name)
        moviesAdapter.setIsTheLastPage(false)
        moviesAdapter.resetList()
        getMoviesList(ApiConstants.DEFAULT_PAGE, genre.id)
    }

    private fun getMoviesGenres() {
        viewModel.getMoviesGenres().observe(this) { res ->
            when (res) {
                is Resource.Success -> {
                    binding.progressBar.setGone()
                    if (res.data != null) {
                        viewModel.genresList = res.data
                    }
                }
                is Resource.Error -> {
                    showToast(res.message.orEmpty())
                    binding.progressBar.setGone()
                }
                is Resource.Loading -> {
                    binding.progressBar.setVisible()
                }
            }
        }
    }

    private fun getMoviesList(
        page: Int,
        genreId: Int
    ) {
        viewModel.currentPage = page
        viewModel.getMoviesList(page, genreId).observe(this) {res ->
            when (res) {
                is Resource.Success -> {
                    if (res.data != null) {
                        viewModel.totalPages = res.data.totalPages
                        moviesAdapter.setIsTheLastPage(viewModel.currentPage == viewModel.totalPages)
                        moviesAdapter.setDifferData(res.data.results)
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
        getMoviesList(viewModel.currentPage + 1, viewModel.selectedGenre?.id.orZero())
    }

    override fun onItemClick(id: Int) {
        DetailActivity.startActivity(this, id)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvMovies.adapter = null
        moviesAdapter.releaseReference()
    }
}