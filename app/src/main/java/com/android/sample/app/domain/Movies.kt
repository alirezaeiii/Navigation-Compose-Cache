package com.android.sample.app.domain

import com.android.sample.app.database.movie.DbMovie
import com.android.sample.app.database.movie.DbMovies
import com.google.gson.annotations.SerializedName

class Movies(
    @SerializedName("image_base") val imageBase: String,
    @SerializedName("movie_offers") val movies: List<Movie>
)

fun Movies.asDatabaseModel(): DbMovies = DbMovies(
    imageBase = this.imageBase,
    movies = this.movies.map {
        DbMovie(id = it.id, price = it.price, image = it.image, available = it.available)
    }
)