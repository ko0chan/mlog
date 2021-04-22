package com.kychan.mlog.model.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MovieDao {
    @Insert
    fun insert(movieEntity: MovieEntity)

    @Insert
    fun insertAll(movieEntity: List<MovieEntity>)
}