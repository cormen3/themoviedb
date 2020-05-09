package com.example.data.datasource

import com.example.data.datasource.movie.MovieDataSource
import com.example.data.datasource.movie.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class DataSourceImplModule {

    @Binds
    @Singleton
    abstract fun dataSourceHome(homeDataSourceImpl: MovieDataSourceImpl): MovieDataSource
}
