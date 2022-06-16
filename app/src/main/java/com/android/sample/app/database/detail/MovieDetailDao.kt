package com.android.sample.app.database.detail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: DbMovieDetail)

    @Query("SELECT * FROM details WHERE id=:id")
    suspend fun getMovieDetail(id: Int): DbMovieDetail?
}