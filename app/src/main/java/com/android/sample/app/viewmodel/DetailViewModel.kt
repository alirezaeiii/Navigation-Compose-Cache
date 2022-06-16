package com.android.sample.app.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.android.sample.app.base.BaseRepository
import com.android.sample.app.base.BaseViewModel
import com.android.sample.app.domain.Movie
import com.android.sample.app.domain.MovieDetail
import com.android.sample.app.ui.Screens.Companion.MOVIE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    repository: BaseRepository<MovieDetail>,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<MovieDetail>(
        repository,
        savedStateHandle.get<Movie>(MOVIE)?.id,
    )