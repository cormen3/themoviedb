package com.example.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.RecyclerItem
import com.example.common.error.ErrorThrowable
import com.example.common_test.TestUtil
import com.example.common_test.mock
import com.example.common_test.observe
import com.example.domain.usecase.GetConfigurationUseCase
import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.usecase.base.invoke
import com.example.presentation.ui.movie.fragments.list.viewmodel.MovieListViewModel
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getMoviesUseCase: GetMoviesUseCase = mock()
    private val getConfigurationUseCase: GetConfigurationUseCase = mock()

    private lateinit var viewModel: MovieListViewModel

    @Test
    fun `get Movies onSuccess`() {
        doReturn(Single.just(TestUtil.fakeMovieHolder())).whenever(getMoviesUseCase).invoke()
        doReturn(Completable.complete()).whenever(getConfigurationUseCase).invoke()

        val moviesObserver = mock<(RecyclerItem) -> Unit>()
        viewModel = createViewModel()
        viewModel.repoResult.observe(moviesObserver)

        verify(moviesObserver, times(1)).invoke(any())
    }

    @Test
    fun `get Movie Details onError`() {
        doReturn(Single.error<ErrorThrowable>(TestUtil.error())).whenever(getMoviesUseCase).invoke()
        doReturn(Completable.error(TestUtil.error())).whenever(getConfigurationUseCase).invoke()

        val moviesObserver = mock<(RecyclerItem) -> Unit>()
        viewModel = createViewModel()
        viewModel.repoResult.observe(moviesObserver)

        verify(moviesObserver, never()).invoke(any())
    }

    private fun createViewModel() = MovieListViewModel(
        getMoviesUseCase,
        getConfigurationUseCase
    )
}
