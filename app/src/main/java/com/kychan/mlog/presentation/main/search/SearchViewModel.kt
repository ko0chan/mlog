package com.kychan.mlog.presentation.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kychan.mlog.model.api.NaverApi
import com.kychan.mlog.model.database.MovieDao
import com.kychan.mlog.model.database.MovieEntity
import com.kychan.mlog.model.response.SearchMovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
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

                    _movieList.value = response.body()?.movieResponseList?.map {
                        it.toSearchMovieItem()
                    }
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    Log.d("TAG", "실패 : $t")
                }

            })
    }

    fun insertMovie(item: SearchMovieItem) {
        movieDao.insert(MovieEntity.of(item))
    }
}