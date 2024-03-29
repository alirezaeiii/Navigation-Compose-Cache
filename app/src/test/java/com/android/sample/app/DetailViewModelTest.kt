package com.android.sample.app

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.android.sample.app.database.detail.MovieDetailDao
import com.android.sample.app.domain.Detail
import com.android.sample.app.domain.Movie
import com.android.sample.app.network.ApiService
import com.android.sample.app.repository.MovieDetailRepository
import com.android.sample.app.ui.Screens
import com.android.sample.app.util.ViewState
import com.android.sample.app.util.isNetworkAvailable
import com.android.sample.app.viewmodel.DetailViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: ApiService

    @Mock
    private lateinit var dao: MovieDetailDao

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        mockkStatic("com.android.sample.app.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
        testCoroutineRule.runBlockingTest {
            `when`(api.getMovieData()).thenReturn(
                Detail(movieData = emptyList())
            )
            `when`(dao.getMovieDetail(anyInt())).thenReturn(null)
        }
        val repository = MovieDetailRepository(dao, api, context, Dispatchers.Main)
        testCoroutineRule.pauseDispatcher()
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        every {
            savedStateHandle.get<Movie>(Screens.MOVIE)
        } returns Movie(1, "32.00€", "", false)
        val viewModel = DetailViewModel(repository, savedStateHandle)
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Loading))

        testCoroutineRule.resumeDispatcher()
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Success(null)))
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        mockkStatic("com.android.sample.app.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
        testCoroutineRule.runBlockingTest {
            `when`(api.getMovieData()).thenThrow(RuntimeException(""))
        }
        val repository = MovieDetailRepository(dao, api, context, Dispatchers.Main)
        testCoroutineRule.pauseDispatcher()
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        every {
            savedStateHandle.get<Movie>(Screens.MOVIE)
        } returns Movie(1, "32.00€", "", false)
        val viewModel = DetailViewModel(repository, savedStateHandle)
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Loading))

        testCoroutineRule.resumeDispatcher()
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Error(errorMsg)))
    }

    @Test
    fun givenNetworkUnAvailable_whenFetch_shouldReturnError() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        mockkStatic("com.android.sample.app.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns false
        testCoroutineRule.runBlockingTest {
            `when`(api.getMovieData()).thenReturn(
                Detail(movieData = emptyList())
            )
            `when`(dao.getMovieDetail(anyInt())).thenReturn(null)
        }
        val repository = MovieDetailRepository(dao, api, context, Dispatchers.Main)
        testCoroutineRule.pauseDispatcher()
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        every {
            savedStateHandle.get<Movie>(Screens.MOVIE)
        } returns Movie(1, "32.00€", "", false)
        val viewModel = DetailViewModel(repository, savedStateHandle)
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Loading))

        testCoroutineRule.resumeDispatcher()
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Error(errorMsg)))
    }
}