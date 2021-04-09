package com.kychan.mlog.presentation.main.search

import java.util.*

data class SearchMovieItem (
    val image: String,
    val title: String,
    val director: String,
    val actor: String,
    val pubDate: Date,
    val userRating: Int
)