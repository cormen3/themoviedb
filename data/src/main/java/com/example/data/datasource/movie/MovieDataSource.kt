package com.example.data.datasource.movie

import com.example.common.RecyclerItem
import com.example.data.entity.model.remote.MovieInfo
import com.example.domain.entity.movie.MovieHolder
import io.reactivex.Completable
import io.reactivex.Single

interface MovieDataSource {
    fun getMovies():Single<MovieHolder<RecyclerItem>>
    fun getConfiguration(): Completable
    fun getMovieDetails(movieId: String): Single<MovieInfo>
}
