package com.kychan.mlog.presentation.main.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class SearchMovieAdapter(private val itemClick: (SearchMovieItem) -> Unit) :
    ListAdapter<SearchMovieItem, SearchMovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder =
        SearchMovieViewHolder(parent, itemClick)

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchMovieItem>() {
            override fun areItemsTheSame(oldItem: SearchMovieItem, newItem: SearchMovieItem): Boolean =
                oldItem.image == newItem.image

            override fun areContentsTheSame(oldItem: SearchMovieItem, newItem: SearchMovieItem): Boolean =
                oldItem == newItem
        }
    }
}