package com.example.data.datasource.movie

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.common.RecyclerItem
import com.example.common.extensions.or
import com.example.common.preferences.PreferencesHelper
import com.example.data.datasource.movie.paging.MovieDataSourceFactory
import com.example.data.entity.model.remote.MovieDetailResponse
import com.example.data.entity.model.remote.MovieInfo
import com.example.data.entity.model.remote.MovieReviewsResponse
import com.example.data.extension.onError
import com.example.data.network.MovieDataService
import com.example.domain.entity.movie.MovieHolder
import com.example.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val preferencesHelper: PreferencesHelper,
    private val api: MovieDataService,
    private val ioExecutor: ThreadExecutor,
    private val config: PagedList.Config,
    private val sourceFactory: MovieDataSourceFactory
) : MovieDataSource {

    override fun getMovies(): Single<MovieHolder<RecyclerItem>> {

        val builder = LivePagedListBuilder(
            sourceFactory.mapByPage { factory -> factory.map { it.toRecyclerItem() } },
            config
        ).setFetchExecutor(ioExecutor)

        val livePagedList = builder.build()

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }

        return Single.fromCallable {
            MovieHolder(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState },
                refreshState = refreshState,
                refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
                retry = { sourceFactory.sourceLiveData.value?.retryAllFailed() }
            )
        }
    }

    override fun getConfiguration(): Completable {
        return api.getConfiguration()
            .onError()
            .flatMapCompletable { config ->
                preferencesHelper.imagesBaseUrl =
                    config.images.baseUrl.plus(config.images.backdropSizes?.get(0)?.or("original"))
                Completable.complete()
            }
    }

    override fun getMovieDetails(movieId: String): Single<MovieInfo> {
        return Single.zip(
            api.getMovieDetails(movieId),
            api.getMovieReviews(movieId),
            BiFunction<MovieDetailResponse, MovieReviewsResponse, MovieInfo> { details, reviews ->
                MovieInfo(details, reviews)
            }
        )
    }
}

