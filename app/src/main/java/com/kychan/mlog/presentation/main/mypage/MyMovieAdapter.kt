package com.kychan.mlog.presentation.main.mypage

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.kychan.mlog.presentation.main.search.SearchMovieItem

class MyMovieAdapter(private val itemClick: (SearchMovieItem) -> Unit) :
    PagedListAdapter<SearchMovieItem, MyMovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieViewHolder =
        MyMovieViewHolder(parent, itemClick)

    override fun onBindViewHolder(holder: MyMovieViewHolder, position: Int) {
        val searchMovieItem: SearchMovieItem? = getItem(position)
        if (searchMovieItem != null) {
            holder.bind(searchMovieItem)
        }
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