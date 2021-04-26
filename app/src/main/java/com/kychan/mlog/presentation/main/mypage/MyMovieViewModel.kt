package com.kychan.mlog.presentation.main.mypage

import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.kychan.mlog.model.database.MovieDao
import com.kychan.mlog.presentation.main.search.SearchMovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyMovieViewModel @Inject constructor(private val movieDao: MovieDao) : ViewModel() {

    val movieList: LiveData<PagedList<SearchMovieItem>> =
        movieDao.getMovieAll().map {
            it.toMovieItem()
        }.toLiveData(pageSize = 10)

    fun deleteMovie(link: String) {
        Thread {
            movieDao.delete(link)
        }.start()
    }
}