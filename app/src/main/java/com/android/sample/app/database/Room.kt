package com.android.sample.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.sample.app.database.detail.DbMovieDetail
import com.android.sample.app.database.movie.DbMovieConverter
import com.android.sample.app.database.detail.MovieDetailDao
import com.android.sample.app.database.movie.DbMovies
import com.android.sample.app.database.movie.MovieDao

@Database(entities = [DbMovies::class, DbMovieDetail::class], version = 1, exportSchema = false)
@TypeConverters(DbMovieConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
}