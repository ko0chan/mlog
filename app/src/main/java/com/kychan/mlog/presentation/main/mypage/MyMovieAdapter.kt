package com.kychan.mlog.presentation.main.mypage

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class MyMovieAdapter(private val itemClick: (MyMovieItem) -> Unit) :
    ListAdapter<MyMovieItem, MyMovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieViewHolder =
        MyMovieViewHolder(parent, itemClick)

    override fun onBindViewHolder(holder: MyMovieViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MyMovieItem>() {
            override fun areItemsTheSame(oldItem: MyMovieItem, newItem: MyMovieItem): Boolean =
                oldItem.image == newItem.image

            override fun areContentsTheSame(oldItem: MyMovieItem, newItem: MyMovieItem): Boolean =
                oldItem == newItem
        }
    }
}