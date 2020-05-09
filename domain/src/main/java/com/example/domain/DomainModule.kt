package com.example.domain

import com.example.common.RecyclerItem
import com.example.domain.entity.movie.MovieHolder
import com.example.domain.entity.movie.ConfigurationObject
import com.example.domain.entity.movie.MovieInfoObject
import com.example.domain.executor.transformer.CTransformer
import com.example.domain.executor.transformer.FTransformer
import com.example.domain.transformer.AsyncCTransformer
import com.example.domain.transformer.AsyncFTransformer
import com.example.domain.transformer.AsyncSTransformer
import com.example.domain.transformer.STransformer
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class DomainModule {

    @Binds
    abstract fun completableTransformer(
        transformer: AsyncCTransformer
    ): CTransformer

    @Binds
    abstract fun intSTransformer(
        transformer: AsyncSTransformer<Boolean>
    ): STransformer<Boolean>

    @Binds
    abstract fun movieInfoSTransformer(
        transformer: AsyncSTransformer<MovieInfoObject>
    ): STransformer<MovieInfoObject>

    @Binds
    abstract fun carSTransformer(
        transformer: AsyncSTransformer<MovieHolder<RecyclerItem>>
    ): STransformer<MovieHolder<RecyclerItem>>

    @Binds
    abstract fun intFTransformer(
        transformer: AsyncFTransformer<ConfigurationObject>
    ): FTransformer<ConfigurationObject>
}
