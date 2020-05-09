package com.example.domain.usecase

import com.example.domain.executor.transformer.CTransformer
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.base.UseCaseCompletable
import io.reactivex.Completable
import javax.inject.Inject

class GetConfigurationUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val transformer: CTransformer
) : UseCaseCompletable<Unit>() {

    override fun execute(param: Unit): Completable =
        repository.getConfiguration().compose(transformer)
}