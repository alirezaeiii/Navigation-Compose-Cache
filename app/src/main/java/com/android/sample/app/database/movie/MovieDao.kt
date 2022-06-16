package com.android.sample.app.database.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: DbMovies)

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): DbMovies?
}