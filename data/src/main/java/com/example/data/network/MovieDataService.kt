package com.example.data.network

import com.example.data.BuildConfig
import com.example.data.entity.model.remote.ConfigurationResponse
import com.example.data.entity.model.remote.MovieDetailResponse
import com.example.data.entity.model.remote.MovieReviewsResponse
import com.example.data.entity.model.remote.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDataService {

    @GET(value = "${BuildConfig.API_VERSION}/movie/now_playing")
    fun getMovies(@Query("page") page: Int): Single<MoviesResponse>

    @GET(value = "${BuildConfig.API_VERSION}/configuration")
    fun getConfiguration(): Single<ConfigurationResponse>

    @GET(value = "${BuildConfig.API_VERSION}/movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: String): Single<MovieDetailResponse>

    @GET(value = "${BuildConfig.API_VERSION}/movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: String): Single<MovieReviewsResponse>
}