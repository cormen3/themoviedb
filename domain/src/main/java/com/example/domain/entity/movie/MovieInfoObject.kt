package com.example.domain.entity.movie

data class MovieInfoObject(
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val title: String?,
    val overview: String?,
    val review: MovieDetailsReviewsObject?
)
