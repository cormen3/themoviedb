package com.example.presentation.ui.movie.fragments.details.viewmodel

import androidx.lifecycle.ViewModel
import com.example.presentation.common.di.PerFragment
import com.example.presentation.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailsViewModelModule {
    @Binds
    @PerFragment
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun viewModel(viewModel: DetailsViewModel): ViewModel
}