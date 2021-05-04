package com.kychan.mlog.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kychan.mlog.data.local.dao.MovieDao
import com.kychan.mlog.model.MovieEntity
import com.kychan.mlog.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movieList: LiveData<PagedList<SearchMovieItem>> =
        movieRepository.getSearchMovieList()

    val total: LiveData<Int> =
        movieRepository.getItemTotal()

    fun setKeyword(searchKeyword: String) {
        movieRepository.setSearchKeyword(searchKeyword)
        Timer().schedule(100) {
            movieRepository.invalidateDataSource()
        }

    }

    fun insertMovie(item: SearchMovieItem) {
        Thread {
            movieRepository.insertMovie(MovieEntity.of(item))
        }.start()
    }

    fun deleteMovie(link: String) {
        Thread {
            movieRepository.deleteMovie(link)
        }.start()
    }
}