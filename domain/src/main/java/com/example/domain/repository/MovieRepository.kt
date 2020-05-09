package com.example.domain.repository

import com.example.common.RecyclerItem
import com.example.domain.entity.movie.MovieHolder
import com.example.domain.entity.movie.MovieInfoObject
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {
    fun getMovies(): Single<MovieHolder<RecyclerItem>>
    fun getMovieDetails(movieId: String): Single<MovieInfoObject>
    fun getConfiguration(): Completable
}