package com.example.common_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.example.common.NetworkState
import com.example.common.RecyclerItem
import com.example.common.error.ErrorCode
import com.example.common.error.ErrorThrowable
import com.example.data.datasource.movie.mapToMovieObject
import com.example.data.datasource.movie.paging.MovieDataSourceFactory
import com.example.data.entity.model.remote.ConfigurationResponse
import com.example.data.entity.model.remote.MovieDetailResponse
import com.example.data.entity.model.remote.MovieReviewsResponse
import com.example.data.entity.model.remote.MoviesResponse
import com.example.domain.entity.movie.MovieHolder
import com.example.domain.entity.movie.MovieInfoObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

object TestUtil {

    private val gson = Gson()

    private fun parseJson(fileName: String): String =
        javaClass.classLoader?.getResourceAsStream("json/$fileName")
            ?.bufferedReader().use { it!!.readText() }

    fun error(): ErrorThrowable = ErrorThrowable(ErrorCode.ERROR_HAPPENED)

    fun configuration(): ConfigurationResponse? {
        val listType = object : TypeToken<ConfigurationResponse>() {}.type
        return gson.fromJson(parseJson("configuration.json"), listType) as ConfigurationResponse
    }

    fun movieDetails(): MovieDetailResponse? {
        val listType = object : TypeToken<MovieDetailResponse>() {}.type
        return gson.fromJson(parseJson("movie-details.json"), listType) as MovieDetailResponse
    }

    fun movieDetailsInfoObject() = MovieInfoObject(
        posterPath = "posterPath",
        backdropPath = "backdropPath",
        releaseDate = "releaseDate",
        title = "title",
        overview = "overview",
        review = null
    )

    fun movieReviews(): MovieReviewsResponse? {
        val listType = object : TypeToken<MovieReviewsResponse>() {}.type
        return gson.fromJson(parseJson("movie-review.json"), listType) as MovieReviewsResponse
    }

    fun movies(): MoviesResponse? {
        val listType = object : TypeToken<MoviesResponse>() {}.type
        return gson.fromJson(parseJson("movies.json"), listType) as MoviesResponse
    }


    fun fakeMovieHolder(sourceFactory: MovieDataSourceFactory): MovieHolder<RecyclerItem> {

        val pagedList = MutableLiveData<PagedList<RecyclerItem>>()
        val items = movies()?.results?.map { it.mapToMovieObject("") }
        pagedList.value = mockPagedList(items!!)

        val networkState =
            Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState }
        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }

        return MovieHolder(
            pagedList,
            networkState,
            refreshState,
            { sourceFactory.sourceLiveData.value?.invalidate() },
            { sourceFactory.sourceLiveData.value?.retryAllFailed() }
        )

    }

    fun fakeMovieHolder(): MovieHolder<RecyclerItem> {
        val pagedList = MutableLiveData<PagedList<RecyclerItem>>()
        val items = movies()?.results?.map { it.mapToMovieObject("") }
        pagedList.value = mockPagedList(items!!)

        val networkState = MutableLiveData<NetworkState>()
        val refreshState = MutableLiveData<NetworkState>()

        return MovieHolder(
            pagedList,
            networkState,
            refreshState,
            ::fakeMethod,
            ::fakeMethod
        )
    }

    private fun fakeMethod() {}

    private fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }
}