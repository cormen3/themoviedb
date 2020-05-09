package com.example.data.datasource.movie.paging

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.example.common.NetworkState
import com.example.common.extensions.or
import com.example.common.preferences.PreferencesHelper
import com.example.data.BuildConfig
import com.example.data.datasource.movie.mapToMovieObject
import com.example.data.network.MovieDataService
import com.example.domain.entity.movie.MovieObject
import com.example.domain.executor.ThreadExecutor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieItemKeyedDataSource(
    private val remote: MovieDataService,
    private val retryExecutor: ThreadExecutor,
    private val preferencesHelper: PreferencesHelper
) : ItemKeyedDataSource<String, MovieObject>() {

    private var retry: (() -> Any)? = null
    private var pageSize = 1
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    override fun getKey(item: MovieObject): String = ""

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<MovieObject>
    ) {
        remote.getMovies(pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                networkState.postValue(NetworkState.LOADING); initialLoad.postValue(
                NetworkState.LOADING
            )
            }
            .map { response ->
                response.results?.filter { it.posterPath.orEmpty().isNotEmpty() }?.map {
                    it.mapToMovieObject(
                        preferencesHelper.imagesBaseUrl.or(BuildConfig.IMAGES_BASE_URL)
                    )
                }?.toMutableList()
            }
            .subscribe(
                { response ->
                    retry = null
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(response!!)
                    pageSize++
                },
                {
                    retry = { loadInitial(params, callback) }
                    val error = NetworkState.error(it.message ?: "unknown error")
                    networkState.postValue(error)
                    initialLoad.postValue(error)
                })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<MovieObject>) {
        remote.getMovies(pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { networkState.postValue(NetworkState.LOADING) }
            .map { response ->
                response.results?.filter { it.posterPath.orEmpty().isNotEmpty() }?.map {
                    it.mapToMovieObject(
                        preferencesHelper.imagesBaseUrl.or(BuildConfig.IMAGES_BASE_URL)
                    )
                }?.toMutableList()
            }
            .subscribe(
                { response ->
                    retry = null
                    networkState.postValue(NetworkState.LOADED)
                    callback.onResult(response!!)
                    pageSize++
                },
                {
                    retry = { loadAfter(params, callback) }
                    val error = NetworkState.error(it.message ?: "unknown error")
                    networkState.postValue(error)
                })
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<MovieObject>
    ) {}
}