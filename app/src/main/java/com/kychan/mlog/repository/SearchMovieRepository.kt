package com.kychan.mlog.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kychan.mlog.factory.SearchMovieDataSourceFactory
import com.kychan.mlog.model.database.MovieDao
import com.kychan.mlog.model.database.MovieEntity
import com.kychan.mlog.presentation.main.search.SearchMovieItem
import javax.inject.Inject

class SearchMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val searchMovieDataSourceFactory: SearchMovieDataSourceFactory
) {
    fun invalidateDataSource() =
        searchMovieDataSourceFactory.liveData.value?.invalidate()

    fun setKeyword(keyword: String) {
        searchMovieDataSourceFactory.setKeyword(keyword)
    }

    fun getItemTotal(): LiveData<Int> {
        return Transformations.switchMap(
            searchMovieDataSourceFactory.liveData, SearchMovieDataSource::itemTotal
        )
    }

    fun getDataFromRemote(): LiveData<PagedList<SearchMovieItem>> {
        return LivePagedListBuilder(
            searchMovieDataSourceFactory,
            SearchMovieDataSourceFactory.pagedListConfig()
        ).build()
    }

    fun getDataFromLocal(): LiveData<PagedList<MovieEntity>> {
        val dataSourceFactory = movieDao.getMovieAll()
        return LivePagedListBuilder(
            dataSourceFactory,
            SearchMovieDataSourceFactory.pagedListConfig()
        ).build()
    }
}
