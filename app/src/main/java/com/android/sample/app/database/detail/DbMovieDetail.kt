package com.android.sample.app.database.detail

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.sample.app.domain.MovieDetail

@Entity(tableName = "details")
class DbMovieDetail(
    @PrimaryKey val id: Int,
    val title: String,
    val subTitle: String
)

fun DbMovieDetail.asDomainModel(): MovieDetail = MovieDetail(
    id = this.id, title = this.title, subTitle = this.subTitle
)