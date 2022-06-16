package com.android.sample.app.database.movie

import androidx.room.TypeConverter
import com.google.gson.Gson

class DbMovieConverter {

    @TypeConverter
    fun jsonToList(value: String): List<DbMovie> =
        Gson().fromJson(value, Array<DbMovie>::class.java).toList()

    @TypeConverter
    fun listToJson(value: List<DbMovie?>) = Gson().toJson(value.filterNotNull())
}