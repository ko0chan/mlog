package com.kychan.mlog.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.kychan.mlog.model.api.NaverApi
import com.kychan.mlog.presentation.main.search.SearchMovieItem
import com.kychan.mlog.repository.SearchMovieDataSource
import javax.inject.Inject

class SearchMovieDataSourceFactory @Inject constructor(
    private val naverApi: NaverApi,
) : DataSource.Factory<Int, SearchMovieItem>() {

    val liveData = MutableLiveData<SearchMovieDataSource>()
    private var searchKeyword: String? = null

    fun setKeyword(keyword: String) {
        searchKeyword = keyword
    }

    override fun create(): DataSource<Int, SearchMovieItem> {
        val source = SearchMovieDataSource(naverApi)
        source.setKeyword(searchKeyword.orEmpty())

        liveData.postValue(source)
        return source.map {
            it.toSearchMovieItem()
        }
    }

    companion object {
        private const val PAGE_SIZE = 10

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}