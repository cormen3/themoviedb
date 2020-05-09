package com.example.presentation.ui.movie.fragments.list.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.presentation.base.BaseFragmentModule
import com.example.presentation.common.connection.ConnectionStatusCallback
import com.example.presentation.common.di.PerFragment
import com.example.presentation.ui.movie.fragments.list.viewmodel.MovieListViewModelModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(
    includes = [
        BaseFragmentModule::class,
        MovieListViewModelModule::class
    ]
)
abstract class MovieListFragmentModule {

    @Binds
    @PerFragment
    @Named(BaseFragmentModule.FRAGMENT)
    abstract fun fragment(movieListFragment: MovieListFragment): Fragment

    @Binds
    @PerFragment
    abstract fun connectionStatusCallback(movieListFragment: MovieListFragment): ConnectionStatusCallback

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideLifecycle(movieListFragment: MovieListFragment): Lifecycle =
            movieListFragment.lifecycle
    }
}