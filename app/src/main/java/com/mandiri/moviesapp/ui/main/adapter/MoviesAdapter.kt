package com.mandiri.moviesapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.moviesapp.common.Constants.ADAPTER_FOOTER
import com.mandiri.moviesapp.common.Constants.LIST_ITEM
import com.mandiri.moviesapp.databinding.ItemFooterBinding
import com.mandiri.moviesapp.databinding.ItemMovieBinding
import com.mandiri.moviesapp.domain.movies.model.list.MoviesItemModel
import com.mandiri.moviesapp.ui.base.LoadingViewHolder

class MoviesAdapter(private var listener: AdapterListener?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<MoviesItemModel>() {
        override fun areItemsTheSame(
            oldItem: MoviesItemModel,
            newItem: MoviesItemModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MoviesItemModel,
            newItem: MoviesItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffCallback)
    private var genreList = mutableListOf<MoviesItemModel>()
    private var isLastPage = false

    fun setDifferData(data: List<MoviesItemModel>) {
        if(data.isEmpty()) return
        genreList.addAll(data)
        listDiffer.submitList(genreList)
        if (genreList.isNotEmpty()) {
            notifyItemChanged(genreList.size)
        }
    }

    fun resetList() {
        genreList.clear()
        listDiffer.submitList(null)
        notifyDataSetChanged()
    }

    fun setIsTheLastPage(value: Boolean) {
        isLastPage = value
    }

    fun releaseReference() {
        listener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ADAPTER_FOOTER) {
            LoadingViewHolder(
                ItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            MoviesViewHolder(
                ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        if (holder is MoviesViewHolder) {
            val data = listDiffer.currentList[position]
            holder.bind(data)
            holder.itemView.setOnClickListener {
                listener?.onItemClick(data.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isLastPage || listDiffer.currentList.isEmpty()) {
            listDiffer.currentList.size
        } else listDiffer.currentList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == listDiffer.currentList.size) {
            ADAPTER_FOOTER
        } else LIST_ITEM
    }

    interface AdapterListener {
        fun loadNextPage()
        fun onItemClick(id: Int)
    }
}