package com.kychan.mlog.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kychan.mlog.model.database.MovieDao
import com.kychan.mlog.model.database.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMovieViewModel @Inject constructor(private val movieDao: MovieDao) : ViewModel() {

    private val _movieList = MutableLiveData<List<MovieEntity>>()
    val movieList: LiveData<List<MovieEntity>>
        get() = _movieList

    fun getMovieAll() {
        viewModelScope.launch {
            movieDao.getMovieAll().collect {
                _movieList.value = it
            }
        }
    }

    fun deleteMovie(link: String) {
        Thread {
            movieDao.delete(link)
        }.start()
    }
}