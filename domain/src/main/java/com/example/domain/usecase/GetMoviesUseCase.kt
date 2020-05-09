package com.example.domain.usecase

import com.example.common.RecyclerItem
import com.example.domain.entity.movie.MovieHolder
import com.example.domain.repository.MovieRepository
import com.example.domain.transformer.STransformer
import com.example.domain.usecase.base.UseCaseSingle
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val transformer: STransformer<MovieHolder<RecyclerItem>>
) : UseCaseSingle<MovieHolder<RecyclerItem>, Unit>() {

    override fun execute(param: Unit): Single<MovieHolder<RecyclerItem>> =
        repository.getMovies().compose(transformer)
}