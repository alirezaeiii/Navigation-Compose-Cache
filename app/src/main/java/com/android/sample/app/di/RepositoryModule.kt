package com.android.sample.app.di

import com.android.sample.app.base.BaseRepository
import com.android.sample.app.domain.MovieDetail
import com.android.sample.app.domain.Movies
import com.android.sample.app.repository.MovieRepository
import com.android.sample.app.repository.MovieDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    internal abstract fun bindMovieRepository(movieRepository: MovieRepository): BaseRepository<Movies>

    @Singleton
    @Binds
    internal abstract fun bindMovieDetailRepository(movieDetailRepository: MovieDetailRepository): BaseRepository<MovieDetail>
}