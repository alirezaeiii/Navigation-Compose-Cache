package com.android.sample.app.network

import com.android.sample.app.domain.Detail
import com.android.sample.app.domain.Movies
import retrofit2.http.GET

interface ApiService {

    @GET("movie_offers.json")
    suspend fun getMovies(): Movies

    @GET("movie_data.json")
    suspend fun getMovieData(): Detail
}