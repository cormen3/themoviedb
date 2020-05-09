package com.example.domain

import com.example.common.error.ErrorThrowable
import com.example.common_test.TestUtil
import com.example.common_test.mock
import com.example.common_test.transformer.TestSTransformer
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.usecase.base.invoke
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetMoviesUseCaseTest {

    private val repository: MovieRepository = mock()
    private val useCase = GetMoviesUseCase(repository, TestSTransformer())

    @Test
    fun `execute onSuccess`() {
        doReturn(Single.just(TestUtil.movies())).whenever(repository).getMovies()

        useCase.invoke().test().assertComplete()

        verify(repository).getMovies()
    }

    @Test
    fun `execute onError`() {
        doReturn(Single.error<ErrorThrowable>(TestUtil.error()))
            .whenever(repository).getMovies()

        useCase.invoke()
            .test()
            .assertNotComplete()

        verify(repository).getMovies()
    }

}