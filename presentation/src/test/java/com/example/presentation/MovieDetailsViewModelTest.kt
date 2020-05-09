package com.example.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.error.ErrorThrowable
import com.example.common_test.TestUtil
import com.example.common_test.mock
import com.example.common_test.observe
import com.example.domain.entity.movie.MovieInfoObject
import com.example.domain.usecase.GetMovieDetailsUseCase
import com.example.presentation.ui.movie.fragments.details.viewmodel.DetailsViewModel
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.anyString
import org.mockito.Mockito.spy

@RunWith(JUnit4::class)
class MovieDetailsViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mock()
    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        viewModel = spy(DetailsViewModel(getMovieDetailsUseCase))
    }

    @Test
    fun `get Movie Details onSuccess`() {
        val movieInfoObserver = mock<(MovieInfoObject) -> Unit>()
        viewModel.movieInfo.observe(movieInfoObserver)

        doReturn(Single.just(TestUtil.movieDetailsInfoObject())).whenever(getMovieDetailsUseCase)
            .invoke(
                anyString()
            )

        viewModel.getMovieDetails()

        verify(movieInfoObserver, times(1)).invoke(any())
    }

    @Test
    fun `get Movie Details onError`() {
        val movieInfoObserver = mock<(MovieInfoObject) -> Unit>()
        viewModel.movieInfo.observe(movieInfoObserver)

        doReturn(Single.error<ErrorThrowable>(TestUtil.error())).whenever(getMovieDetailsUseCase)
            .invoke(
                anyString()
            )

        viewModel.getMovieDetails()

        verify(movieInfoObserver, never()).invoke(any())
    }
}
