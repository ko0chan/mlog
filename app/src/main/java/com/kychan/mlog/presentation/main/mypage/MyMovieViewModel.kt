package com.kychan.mlog.presentation.main.mypage

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.kychan.mlog.data.local.dao.MovieDao
import com.kychan.mlog.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyMovieViewModel @Inject constructor(
    private val movieDao: MovieDao,
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movieList: LiveData<PagedList<MyMovieItem>> by lazy {
        movieRepository.getMovieAll()
    }

    fun deleteMovie(link: String) {
        Thread {
            movieDao.delete(link)
        }.start()
    }

    fun updateMovie(rating: Float, link: String) {
        Thread {
            movieDao.update(rating, link)
        }.start()
    }
}