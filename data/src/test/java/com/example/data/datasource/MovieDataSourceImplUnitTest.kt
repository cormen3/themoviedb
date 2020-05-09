package com.example.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.example.common.error.ErrorThrowable
import com.example.common.preferences.PreferencesHelper
import com.example.common_test.TestUtil
import com.example.common_test.mock
import com.example.data.datasource.movie.MovieDataSourceImpl
import com.example.data.datasource.movie.paging.MovieDataSourceFactory
import com.example.data.entity.model.remote.MovieInfo
import com.example.data.network.MovieDataService
import com.example.domain.executor.ThreadExecutor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieDataSourceImplUnitTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun params() = listOf(0)
    }

    @Suppress("unused")
    @get:Rule // used to make all live data calls sync
    val instantExecutor = InstantTaskExecutorRule()

    private val preferencesHelper: PreferencesHelper = mock()
    private val fakeApi: FakeMovieApi = FakeMovieApi()
    private val remote: MovieDataService = mock()
    private val ioExecutor: ThreadExecutor = mock()
    private val config: PagedList.Config = mock()
    private val sourceFactory = MovieDataSourceFactory(remote, ioExecutor, preferencesHelper)

    private lateinit var dataSource: MovieDataSourceImpl

    @Before
    fun setup() {
        this.dataSource =
            spy(MovieDataSourceImpl(preferencesHelper, remote, ioExecutor, config, sourceFactory))
    }

    @Test
    fun `get configuration onSuccess`() {
        doReturn(fakeApi.getConfiguration()).whenever(remote).getConfiguration()

        dataSource.getConfiguration()
            .test()
            .assertComplete()
    }

    @Test
    fun `get configuration onError`() {
        doReturn(Single.error<ErrorThrowable>(TestUtil.error())).whenever(remote).getConfiguration()

        dataSource.getConfiguration()
            .test()
            .assertNotComplete()
    }

    @Test
    fun `get movie details onSuccess`() {
        val movieDetails = TestUtil.movieDetails()
        val movieReviews = TestUtil.movieReviews()

        doReturn(fakeApi.getMovieDetails(movieDetails)).whenever(remote)
            .getMovieDetails(anyString())
        doReturn(fakeApi.getMovieReviews(movieReviews)).whenever(remote)
            .getMovieReviews(anyString())

        dataSource.getMovieDetails(anyString())
            .test()
            .assertValue(MovieInfo(movieDetails!!, movieReviews!!))
            .assertComplete()
    }

    @Test
    fun `get movie details onError`() {
        doReturn(Single.error<ErrorThrowable>(TestUtil.error())).whenever(remote)
            .getMovieDetails(anyString())
        doReturn(Single.error<ErrorThrowable>(TestUtil.error())).whenever(remote)
            .getMovieReviews(anyString())

        dataSource.getMovieDetails(anyString())
            .test()
            .assertNotComplete()
    }

    @Test
    fun `get movie list onSuccess`() {
        doReturn(Single.just(TestUtil.movies())).whenever(remote).getMovies(anyInt())

        dataSource.getMovies()
            .test()
            .assertComplete()
    }
}
