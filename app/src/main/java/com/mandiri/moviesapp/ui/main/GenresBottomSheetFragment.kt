package com.mandiri.moviesapp.ui.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.core.view.doOnLayout
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mandiri.moviesapp.R
import com.mandiri.moviesapp.databinding.FragmentGenresBottomSheetBinding
import com.mandiri.moviesapp.databinding.ItemGenreBinding
import com.mandiri.moviesapp.domain.movies.model.genres.MoviesGenresItemModel

class GenresBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentGenresBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenresBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogHeight(view)
        generateRadioButton()
    }

    private fun setDialogHeight(view: View) {
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val bottomSheet = (dialog as BottomSheetDialog).findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            ) as FrameLayout
            BottomSheetBehavior.from(bottomSheet).apply {
                skipCollapsed = true
                state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun generateRadioButton() {
        val genresList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelableArray(EXTRA_GENRES_DATA, MoviesGenresItemModel::class.java)
        } else {
            arguments?.getParcelableArray(EXTRA_GENRES_DATA) as? Array<MoviesGenresItemModel>
        }
        val selectedGenre = arguments?.getInt(EXTRA_SELECTED_GENRE)
        genresList?.forEach { data ->
            val itemGenreBinding = ItemGenreBinding.inflate(layoutInflater, binding.root, false)
            with(itemGenreBinding.radioButton) {
                text = data.name
                tag = data.id
                doOnLayout {
                    this.isChecked = data.id == selectedGenre
                }
                setOnClickListener {
                    itemClickListener(data)
                }
            }
            binding.radioGroupGenres.addView(itemGenreBinding.radioButton)
        }
    }

    private fun itemClickListener(data: MoviesGenresItemModel) {
        setFragmentResult(
            REQ_GENRE_KEY,
            bundleOf(EXTRA_GENRES_DATA to data)
        )
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXTRA_SELECTED_GENRE = "EXTRA_SELECTED_GENRE"
        const val EXTRA_GENRES_DATA = "EXTRA_GENRES_DATA"
        const val REQ_GENRE_KEY = "REQ_GENRE_KEY"
        const val TAG = "GENRE_TAG"
        fun newInstance(genresList: List<MoviesGenresItemModel>, selectedGenre: Int) =
            GenresBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray(EXTRA_GENRES_DATA, genresList.toTypedArray())
                    putInt(EXTRA_SELECTED_GENRE, selectedGenre)
                }
            }
    }
}