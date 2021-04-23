package com.kychan.mlog.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyMovieViewModel : ViewModel() {

    private val _movieList = MutableLiveData<List<MyMovieItem>>()
    val movieList: LiveData<List<MyMovieItem>>
        get() = _movieList

    fun getMyMovie() {
        _movieList.value = listOf(
            MyMovieItem("", "11111111111111111", 1.0f),
            MyMovieItem("", "2", 1.5f),
            MyMovieItem("", "3", 2.0f),
            MyMovieItem("", "4", 2.5f),
            MyMovieItem("", "5", 3.0f),
            MyMovieItem("", "6", 3.5f),
            MyMovieItem("", "7", 4.0f),
        )
    }
}