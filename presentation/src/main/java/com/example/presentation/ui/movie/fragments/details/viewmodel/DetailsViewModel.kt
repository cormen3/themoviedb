package com.example.presentation.ui.movie.fragments.details.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.domain.entity.movie.MovieInfoObject
import com.example.domain.usecase.GetMovieDetailsUseCase
import com.example.presentation.base.BaseViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    var movieInfo = MutableLiveData<MovieInfoObject>()
    var movieId = MutableLiveData<Int>()

    fun getMovieDetails() {
        getMovieDetailsUseCase.invoke(movieId.value.toString()).onError().subscribe({
            movieInfo.value = it
        }, {
        }).track()
    }
}
