package com.example.presentation.ui.movie.fragments.details.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.presentation.base.BaseFragmentModule
import com.example.presentation.common.connection.ConnectionStatusCallback
import com.example.presentation.common.di.PerFragment
import com.example.presentation.ui.movie.fragments.details.viewmodel.DetailsViewModelModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(
    includes = [
        BaseFragmentModule::class,
        DetailsViewModelModule::class
    ]
)
abstract class DetailsFragmentModule {

    @Binds
    @PerFragment
    @Named(BaseFragmentModule.FRAGMENT)
    abstract fun fragment(detailsFragment: DetailsFragment): Fragment

    @Binds
    @PerFragment
    abstract fun connectionStatusCallback(detailsFragment: DetailsFragment): ConnectionStatusCallback

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideLifecycle(detailsFragment: DetailsFragment): Lifecycle =
            detailsFragment.lifecycle
    }
}