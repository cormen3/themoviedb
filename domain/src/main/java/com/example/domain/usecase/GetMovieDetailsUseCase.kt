package com.example.domain.usecase

import com.example.domain.entity.movie.MovieInfoObject
import com.example.domain.repository.MovieRepository
import com.example.domain.transformer.STransformer
import com.example.domain.usecase.base.UseCaseSingle
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val transformer: STransformer<MovieInfoObject>
) : UseCaseSingle<MovieInfoObject, String>() {

    override fun execute(param: String): Single<MovieInfoObject> =
        repository.getMovieDetails(param).compose(transformer)
}