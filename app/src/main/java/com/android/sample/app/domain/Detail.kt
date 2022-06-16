package com.android.sample.app.domain

import com.google.gson.annotations.SerializedName

class Detail(
    @SerializedName("movie_data") val movieData: List<MovieDetail>,
)