package com.example.data.datasource.movie.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.common.preferences.PreferencesHelper
import com.example.data.network.MovieDataService
import com.example.domain.entity.movie.MovieObject
import com.example.domain.executor.ThreadExecutor
import javax.inject.Inject


class MovieDataSourceFactory @Inject constructor(
    private val remote: MovieDataService,
    private val retryExecutor: ThreadExecutor,
    private val preferencesHelper: PreferencesHelper
) : DataSource.Factory<String, MovieObject>() {
    val sourceLiveData = MutableLiveData<MovieItemKeyedDataSource>()

    override fun create(): DataSource<String, MovieObject> {
        val source = MovieItemKeyedDataSource(remote, retryExecutor, preferencesHelper)
        sourceLiveData.postValue(source)
        return source
    }
}

