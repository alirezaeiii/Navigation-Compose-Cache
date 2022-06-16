package com.android.sample.app.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("movie_id") val id: Int,
    val price: String,
    val image: String,
    val available: Boolean
) : Parcelable