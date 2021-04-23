package com.kychan.mlog.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getMovieAll(): Flow<List<MovieEntity>>

    @Insert
    fun insert(movieEntity: MovieEntity)

    @Insert
    fun insertAll(movieEntity: List<MovieEntity>)

    @Query("DELETE FROM movie_table WHERE link = (:link)")
    fun delete(link: String)
}