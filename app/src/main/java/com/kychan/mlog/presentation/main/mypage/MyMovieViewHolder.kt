package com.kychan.mlog.presentation.main.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kychan.mlog.databinding.ItemMyMovieBinding
import com.kychan.mlog.ext.setImage
import com.kychan.mlog.presentation.main.search.SearchMovieItem

class MyMovieViewHolder(
    parent: ViewGroup,
    private val itemClick: (SearchMovieItem) -> Unit,
    private val binding: ItemMyMovieBinding =
        ItemMyMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchMovieItem) {
        with(binding) {
            bookmark.setOnClickListener {
                itemClick(item)
            }
            movieImage.setImage(item.image)
            title.text = item.title
            userRating.rating = item.userRating / 2
        }
    }
}