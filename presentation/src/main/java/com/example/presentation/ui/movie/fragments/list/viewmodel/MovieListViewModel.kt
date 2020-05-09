package com.example.presentation.ui.movie.fragments.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.example.common.RecyclerItem
import com.example.domain.entity.movie.MovieHolder
import com.example.domain.usecase.GetConfigurationUseCase
import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.usecase.base.invoke
import com.example.presentation.base.BaseViewModel
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    getConfigurationUseCase: GetConfigurationUseCase
) : BaseViewModel() {

    val repoResult = MutableLiveData<RecyclerItem>()
    var movies: PagedList<RecyclerItem>? = null

    init {
        getConfigurationUseCase.invoke().subscribe({}, {}).track()
        getMovies()
    }

    val items =
        Transformations.switchMap(repoResult) { (it as MovieHolder<RecyclerItem>).pagedList }
    val networkState =
        Transformations.switchMap(repoResult) { (it as MovieHolder<RecyclerItem>).networkState }
    val refreshState =
        Transformations.switchMap(repoResult) { (it as MovieHolder<RecyclerItem>).refreshState }

    private fun getMovies() {
        getMoviesUseCase.invoke().onError().subscribe({
            repoResult.value = it
        }, {}).track()
    }

    fun refresh() {
        (repoResult.value as? MovieHolder<RecyclerItem>)?.refresh?.invoke()
    }

    fun retry() {
        (repoResult.value as? MovieHolder<RecyclerItem>)?.retry?.invoke()
    }
}
