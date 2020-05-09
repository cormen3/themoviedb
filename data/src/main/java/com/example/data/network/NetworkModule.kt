package com.example.data.network

import androidx.paging.PagedList
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class NetworkModule {
    @Provides
    @Reusable
    fun homeDataService(dataServiceFactory: DataServiceFactory) =
        dataServiceFactory.create(MovieDataService::class.java)

    @Provides
    @Reusable
    fun providePagingConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPrefetchDistance(1)
            .build()
    }
}

