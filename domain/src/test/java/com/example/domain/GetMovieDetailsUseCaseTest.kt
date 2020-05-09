package com.example.domain

import com.example.common.error.ErrorThrowable
import com.example.common_test.TestUtil
import com.example.common_test.mock
import com.example.common_test.transformer.TestSTransformer
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovieDetailsUseCase
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetMovieDetailsUseCaseTest {

    private val repository: MovieRepository = mock()
    private val useCase = GetMovieDetailsUseCase(repository, TestSTransformer())

    @Test
    fun `execute onSuccess`() {
        val movieId = "someMovieId"
        doReturn(Single.just(TestUtil.movieDetails())).whenever(repository).getMovieDetails(movieId)

        useCase(movieId).test().assertComplete()

        verify(repository).getMovieDetails(movieId)
    }

    @Test
    fun `execute onError`() {
        val movieId = "someMovieId"
        doReturn(Single.error<ErrorThrowable>(TestUtil.error()))
            .whenever(repository).getMovieDetails(movieId)

        useCase.invoke(movieId)
            .test()
            .assertNotComplete()

        verify(repository).getMovieDetails(movieId)
    }

}