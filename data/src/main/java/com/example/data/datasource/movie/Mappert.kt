package com.example.data.datasource.movie

import com.example.common.RecyclerItem
import com.example.data.entity.model.remote.Movie
import com.example.domain.entity.movie.MovieObject

fun Movie.mapToMovieObject(baseUrl: String) = MovieObject(
    popularity,
    voteCount,
    video,
    posterPath.attachBaseUrl(baseUrl),
    movieId,
    adult,
    backdropPath.attachBaseUrl(baseUrl),
    originalLanguage,
    originalTitle,
    genreIds,
    title,
    voteAverage,
    overview,
    releaseDate
)

fun String?.attachBaseUrl(baseUrl: String): String? {
    return baseUrl.plus(this)
}

fun MovieObject.toRecyclerItem(): RecyclerItem {
    return this
}