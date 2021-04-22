package com.kychan.mlog.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kychan.mlog.model.database.MovieDao
import com.kychan.mlog.model.database.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyMovieViewModel @Inject constructor(private val movieDao: MovieDao) : ViewModel() {

    val movieList: LiveData<List<MovieEntity>> = movieDao.getMovieAll()

}