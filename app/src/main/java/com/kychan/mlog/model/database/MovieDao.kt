package com.kychan.mlog.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getMovieAll(): LiveData<List<MovieEntity>>

    @Insert
    fun insert(movieEntity: MovieEntity)

    @Insert
    fun insertAll(movieEntity: List<MovieEntity>)
}