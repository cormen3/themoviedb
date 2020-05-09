package com.example.presentation.ui.movie.fragments.list.viewmodel

import androidx.lifecycle.ViewModel
import com.example.presentation.common.di.PerFragment
import com.example.presentation.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieListViewModelModule {
    @Binds
    @PerFragment
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun viewModel(viewModel: MovieListViewModel): ViewModel
}