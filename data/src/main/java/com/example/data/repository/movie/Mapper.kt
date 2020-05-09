package com.example.data.repository.movie

import com.example.data.BuildConfig
import com.example.data.entity.model.remote.MovieInfo
import com.example.data.entity.model.remote.MovieReview
import com.example.data.entity.model.remote.MovieReviewsResponse
import com.example.domain.entity.movie.MovieDetailsReviewsObject
import com.example.domain.entity.movie.MovieInfoObject
import com.example.domain.entity.movie.MovieReviewObject

fun MovieInfo.toMovieInfoObject() = MovieInfoObject(
    movieDetailResponse.posterPath.attachBaseUrl(),
    movieDetailResponse.backdropPath.attachBaseUrl(),
    movieDetailResponse.releaseDate,
    movieDetailResponse.originalTitle,
    movieDetailResponse.overview,
    movieReviewsResponse.toReviewObject()
)

fun String?.attachBaseUrl(): String = BuildConfig.IMAGES_BASE_URL.plus("original") + this

private fun MovieReviewsResponse.toReviewObject() = MovieDetailsReviewsObject(
    reviews?.map { it.toReviewObject() }
)

private fun MovieReview.toReviewObject() = MovieReviewObject(author, content)


