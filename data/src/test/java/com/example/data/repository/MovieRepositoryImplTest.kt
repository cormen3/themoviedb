package com.example.data.repository

import com.example.common_test.mock
import com.example.data.datasource.movie.MovieDataSource
import com.example.data.repository.movie.MovieRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieRepositoryImplTest {

    private var dataSource: MovieDataSource = mock()
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        this.repository = MovieRepositoryImpl(dataSource)
    }

    @Test
    fun `login - success`() {
    }

    @Test
    fun `login - error`() {
    }
}
