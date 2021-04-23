package com.kychan.mlog.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kychan.mlog.R
import com.kychan.mlog.databinding.ItemMovieBinding
import com.kychan.mlog.ext.setImage

class SearchMovieViewHolder(
    parent: ViewGroup,
    private val itemClick: (SearchMovieItem) -> Unit,
    private val binding: ItemMovieBinding =
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchMovieItem) {
        with(binding) {
            bookmark.setOnClickListener {
                itemClick(item)
                bookmark.setImageResource(R.drawable.ic_bookmark)
            }
            if (item.isMyMovie) {
                bookmark.setImageResource(R.drawable.ic_bookmark)
            } else {
                bookmark.setImageResource(R.drawable.ic_bookmark_border)
            }
            movieImage.setImage(item.image)
            title.text = item.title
            director.text = item.director
            actor.text = item.actor
            pubDate.text = item.pubDate
            userRating.rating = item.userRating / 2
        }
    }
}