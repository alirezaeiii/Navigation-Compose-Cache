package com.android.sample.app.ui

sealed class Screens(val title: String) {
    object Dashboard : Screens("main_screen")
    object Section : Screens("detail_screen/{$MOVIE}")

    companion object {
        const val MOVIE = "movie"
    }
}