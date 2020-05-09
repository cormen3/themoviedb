package com.example.domain

import com.example.common_test.mock
import com.example.common_test.transformer.TestCTransformer
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetConfigurationUseCase
import com.example.domain.usecase.base.invoke
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetConfigurationUseCaseTest {

    private val repository: MovieRepository = mock()
    private val useCase = GetConfigurationUseCase(repository, TestCTransformer())

    @Test
    fun execute() {
        doReturn(Completable.complete()).whenever(repository).getConfiguration()

        useCase.invoke().test().assertComplete()

        verify(repository).getConfiguration()
    }
}