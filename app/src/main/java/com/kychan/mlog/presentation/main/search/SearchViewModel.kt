package com.kychan.mlog.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kychan.mlog.model.database.MovieDao
import com.kychan.mlog.model.database.MovieEntity
import com.kychan.mlog.repository.SearchMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieDao: MovieDao,
    private val searchMovieRepository: SearchMovieRepository
) : ViewModel() {

    val movieList: LiveData<PagedList<SearchMovieItem>> =
        searchMovieRepository.getDataFromRemote()

    val total: LiveData<Int> =
        searchMovieRepository.getItemTotal()

    fun setKeyword(searchKeyword: String) {
        searchMovieRepository.setKeyword(searchKeyword)
        searchMovieRepository.invalidateDataSource()
    }
//
//    private fun searchMovieItemListOf(
//        searchMovieList: List<SearchMovieResponse.MovieResponse>,
//        myMovieLinkList: List<String>?
//    ): List<SearchMovieItem> {
//        if (myMovieLinkList.isNullOrEmpty()) {
//            return searchMovieList.map {
//                it.toSearchMovieItem()
//            }
//        }
//
//        val list = mutableListOf<SearchMovieItem>()
//        searchMovieList.map {
//            it.toSearchMovieItem()
//        }.forEach { searchMovieItem ->
//            list.add(
//                SearchMovieItem(
//                    image = searchMovieItem.image,
//                    title = searchMovieItem.title,
//                    link = searchMovieItem.link,
//                    subTitle = searchMovieItem.subTitle,
//                    director = searchMovieItem.director,
//                    actor = searchMovieItem.actor,
//                    pubDate = searchMovieItem.pubDate,
//                    userRating = searchMovieItem.userRating,
//                    isMyMovie = myMovieLinkList.contains(searchMovieItem.link)
//                )
//            )
//        }
//        return list
//    }

    fun insertMovie(item: SearchMovieItem) {
        Thread {
            movieDao.insert(MovieEntity.of(item))
        }.start()
    }

    fun deleteMovie(link: String) {
        Thread {
            movieDao.delete(link)
        }.start()
    }
}