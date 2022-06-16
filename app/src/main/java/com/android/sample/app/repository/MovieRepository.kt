package com.android.sample.app.repository

import android.content.Context
import com.android.sample.app.base.BaseRepository
import com.android.sample.app.database.movie.MovieDao
import com.android.sample.app.database.movie.asDomainModel
import com.android.sample.app.di.IoDispatcher
import com.android.sample.app.domain.Movies
import com.android.sample.app.domain.asDatabaseModel
import com.android.sample.app.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val dao: MovieDao,
    private val api: ApiService,
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseRepository<Movies>(context, ioDispatcher) {

    override suspend fun query(id: Int?): Movies? =
        dao.getMovies()?.asDomainModel()

    override suspend fun fetch(id: Int?): Movies = api.getMovies()

    override suspend fun saveFetchResult(t: Movies?) {
        t?.asDatabaseModel()?.let { dao.insert(it) }
    }
}