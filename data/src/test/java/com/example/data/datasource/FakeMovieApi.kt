package com.example.data.datasource

import com.example.data.entity.model.remote.ConfigurationResponse
import com.example.data.entity.model.remote.MovieDetailResponse
import com.example.data.entity.model.remote.MovieReviewsResponse
import com.example.data.entity.model.remote.MoviesResponse
import com.example.data.network.MovieDataService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.Single
import java.io.IOException

class FakeMovieApi : MovieDataService {

    private val gson = Gson()

    private fun parseJson(fileName: String): String =
        javaClass.classLoader?.getResourceAsStream("json/$fileName")
            ?.bufferedReader().use { it!!.readText() }

    override fun getMovies(page: Int): Single<MoviesResponse> {
        val listType = object : TypeToken<MoviesResponse>() {}.type
        val response = gson.fromJson(parseJson("movies.json"), listType) as MoviesResponse
        return Single.just(response)
    }

    override fun getConfiguration(): Single<ConfigurationResponse> {
        val listType = object : TypeToken<ConfigurationResponse>() {}.type
        val response =
            gson.fromJson(parseJson("configuration.json"), listType) as ConfigurationResponse
        return Single.just(response)
    }

    fun getMovieDetails(movieDetailResponse: MovieDetailResponse?): Single<MovieDetailResponse> {
        return Single.just(movieDetailResponse)
    }

    fun getMovieReviews(movieReviewsResponse: MovieReviewsResponse?): Single<MovieReviewsResponse> {
        return Single.just(movieReviewsResponse)
    }

    override fun getMovieDetails(movieId: String): Single<MovieDetailResponse> {
        return Single.just(MovieDetailResponse())
    }

    override fun getMovieReviews(id: String): Single<MovieReviewsResponse> {
        val listType = object : TypeToken<MovieReviewsResponse>() {}.type
        val response =  gson.fromJson(parseJson("movie-review.json"), listType) as MovieReviewsResponse
        return Single.just(response)
    }

//    override fun getCarTypes(
//        id: String?,
//        page: Int,
//        pageSize: Int,
//        wa_key: String
//    ): Single<CarDataResponse> {
//        return Single.just(CarDataResponse(0, 0, 0, mapOf()))
//    }
//
//    private val model = mutableMapOf<String, Manufacture>()
//    var failureMsg: String? = null
//
//    fun addPost(post: ManufactureObject?) {
//        val manufactures = model.getOrPut("test") { Manufacture(items = arrayListOf()) }
//        manufactures.items.add(post!!)
//    }
//
//    fun clear() {
//        model.clear()
//    }
//
//    private fun findPosts(pageSize: Int): Map<String, String> {
//        val manufacture = findManufacture()
//        val posts = manufacture.findPosts(pageSize)
//        return posts.associateBy({ it.manufactureId }, { it.manufactureName })
//    }
//
//    private fun findManufacture(): FakeMovieApi.Manufacture {
//        return model.getOrDefault("test", Manufacture())
//    }
//
//    override fun getManufactures(
//        page: Int,
//        pageSize: Int,
//        wa_key: String
//    ): Single<CarDataResponse> {
//        failureMsg?.let { return Single.error(IOException(it)) }
//        val items = findPosts(pageSize)
//        val response = CarDataResponse(page, pageSize, 0, items)
//        return Single.just(response)
//    }
//
//    private class Manufacture(val items: MutableList<ManufactureObject> = arrayListOf()) {
//        fun findPosts(pageSize: Int): List<ManufactureObject> {
//            return items.subList(0, Math.min(items.size, pageSize))
//        }
//    }

}