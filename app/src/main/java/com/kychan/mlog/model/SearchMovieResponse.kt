package com.kychan.mlog.model

import com.google.gson.annotations.SerializedName
import com.kychan.mlog.presentation.main.search.SearchMovieItem

class SearchMovieResponse(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val movieResponseList: List<MovieResponse>
) {
    data class MovieResponse(
        @SerializedName("title")
        val title: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("subtitle")
        val subTitle: String,
        @SerializedName("pubDate")
        val pubDate: String,
        @SerializedName("director")
        val director: String,
        @SerializedName("actor")
        val actor: String,
        @SerializedName("userRating")
        val userRating: Float
    ) {
        fun toSearchMovieItem(): SearchMovieItem {
            val replaceTitle = title.replace("<b>", "").replace("</b>", "")
            return SearchMovieItem(
                image = image,
                title = replaceTitle,
                link = link,
                subTitle = subTitle,
                director = director,
                actor = actor,
                pubDate = pubDate,
                userRating = userRating,
                isMyMovie = false
            )
        }
    }
}
