package com.android.sample.app.repository

import android.content.Context
import com.android.sample.app.base.BaseRepository
import com.android.sample.app.database.detail.MovieDetailDao
import com.android.sample.app.database.detail.asDomainModel
import com.android.sample.app.di.IoDispatcher
import com.android.sample.app.domain.MovieDetail
import com.android.sample.app.domain.asDatabaseModel
import com.android.sample.app.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailRepository @Inject constructor(
    private val dao: MovieDetailDao,
    private val api: ApiService,
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseRepository<MovieDetail>(context, ioDispatcher) {

    override suspend fun query(id: Int?): MovieDetail? =
        dao.getMovieDetail(id!!)?.asDomainModel()

    override suspend fun fetch(id: Int?): MovieDetail? =
        api.getMovieData().movieData.find { it.id == id }

    override suspend fun saveFetchResult(t: MovieDetail?) {
        t?.asDatabaseModel()?.let { dao.insert(it) }
    }
}