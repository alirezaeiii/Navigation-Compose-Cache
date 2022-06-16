package com.android.sample.app.domain

import com.android.sample.app.database.detail.DbMovieDetail
import com.google.gson.annotations.SerializedName

class MovieDetail(
    @SerializedName("movie_id") val id: Int,
    val title: String,
    @SerializedName("sub_title") val subTitle: String
)

fun MovieDetail.asDatabaseModel(): DbMovieDetail = DbMovieDetail(
    id = this.id, title = this.title, subTitle = this.subTitle
)