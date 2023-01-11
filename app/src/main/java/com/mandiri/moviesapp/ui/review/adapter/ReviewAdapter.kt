package com.mandiri.moviesapp.ui.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.moviesapp.common.Constants
import com.mandiri.moviesapp.databinding.ItemFooterBinding
import com.mandiri.moviesapp.databinding.ItemReviewBinding
import com.mandiri.moviesapp.domain.movies.model.reviews.MoviesReviewsItemModel
import com.mandiri.moviesapp.ui.base.LoadingViewHolder

class ReviewAdapter(private var listener: AdapterListener?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<MoviesReviewsItemModel>() {
        override fun areItemsTheSame(
            oldItem: MoviesReviewsItemModel,
            newItem: MoviesReviewsItemModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MoviesReviewsItemModel,
            newItem: MoviesReviewsItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffCallback)
    private var reviewList = mutableListOf<MoviesReviewsItemModel>()
    private var isLastPage = false

    fun setDifferData(data: List<MoviesReviewsItemModel>) {
        if(data.isEmpty()) return
        reviewList.addAll(data)
        listDiffer.submitList(reviewList)
        if (reviewList.isNotEmpty()) {
            notifyItemChanged(reviewList.size)
        }
    }

    fun setIsTheLastPage(value: Boolean) {
        isLastPage = value
    }

    fun releaseReference() {
        listener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.ADAPTER_FOOTER) {
            LoadingViewHolder(
                ItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            ReviewViewHolder(
                ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoadingViewHolder) {
            if (!isLastPage) {
                listener?.loadNextPage()
            }
            return
        }
        if (holder is ReviewViewHolder) {
            holder.bind(listDiffer.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return if (isLastPage || listDiffer.currentList.isEmpty()) {
            listDiffer.currentList.size
        } else listDiffer.currentList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == listDiffer.currentList.size) {
            Constants.ADAPTER_FOOTER
        } else Constants.LIST_ITEM
    }

    interface AdapterListener {
        fun loadNextPage()
    }
}