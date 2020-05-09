package com.example.data.repository.movie

import com.example.common.RecyclerItem
import com.example.data.datasource.movie.MovieDataSource
import com.example.domain.entity.movie.MovieHolder
import com.example.domain.entity.movie.MovieInfoObject
import com.example.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: MovieDataSource
) : MovieRepository {

    override fun getMovies(): Single<MovieHolder<RecyclerItem>> =
        dataSource.getMovies()

    override fun getMovieDetails(movieId: String): Single<MovieInfoObject> =
        dataSource.getMovieDetails(movieId).map { it.toMovieInfoObject() }

    override fun getConfiguration(): Completable = dataSource.getConfiguration()
}


