package com.kychan.mlog.presentation.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kychan.mlog.model.api.NaverApi
import com.kychan.mlog.model.database.MovieDao
import com.kychan.mlog.model.database.MovieEntity
import com.kychan.mlog.model.response.SearchMovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieDao: MovieDao) : ViewModel() {

    private val _movieList = MutableLiveData<List<SearchMovieItem>>()
    val movieList: LiveData<List<SearchMovieItem>>
        get() = _movieList

    private val myMovieList = MutableLiveData<List<String>>()

    init {
        viewModelScope.launch {
            movieDao.getMovieAll().collect {
                myMovieList.value = it.map { movieEntity ->
                    movieEntity.toMovieItem().link
                }
            }
        }
    }

    fun getSearchMovie(searchKeyword: String) {
        if (searchKeyword.isEmpty()) return

        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaverApi::class.java)
            .getSearchMovie(query = searchKeyword)
            .enqueue(object : Callback<SearchMovieResponse> {
                override fun onResponse(call: Call<SearchMovieResponse>, response: Response<SearchMovieResponse>) {
                    Log.d("TAG", "성공 : ${response.raw()}")
                    Log.d("TAG", "성공 : ${response.body()?.movieResponseList}")

                    _movieList.value = searchMovieItemListOf(response.body()?.movieResponseList.orEmpty(), myMovieList.value)
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    Log.d("TAG", "실패 : $t")
                }
            })
    }

    private fun searchMovieItemListOf(
        searchMovieList: List<SearchMovieResponse.MovieResponse>,
        myMovieLinkList: List<String>?
    ): List<SearchMovieItem> {
        if (myMovieLinkList.isNullOrEmpty()) {
            return searchMovieList.map {
                it.toSearchMovieItem()
            }
        }

        val list = mutableListOf<SearchMovieItem>()
        searchMovieList.map {
            it.toSearchMovieItem()
        }.forEach { searchMovieItem ->
            list.add(
                SearchMovieItem(
                    image = searchMovieItem.image,
                    title = searchMovieItem.title,
                    link = searchMovieItem.link,
                    subTitle = searchMovieItem.subTitle,
                    director = searchMovieItem.director,
                    actor = searchMovieItem.actor,
                    pubDate = searchMovieItem.pubDate,
                    userRating = searchMovieItem.userRating,
                    isMyMovie = myMovieLinkList.contains(searchMovieItem.link)
                )
            )
        }
        return list
    }

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