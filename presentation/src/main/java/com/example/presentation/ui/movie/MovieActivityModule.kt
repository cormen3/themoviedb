package com.example.presentation.ui.movie

import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.base.BaseActivityModule
import com.example.presentation.common.di.PerFragment
import com.example.presentation.di.PerActivity
import com.example.presentation.ui.movie.fragments.details.view.DetailsFragment
import com.example.presentation.ui.movie.fragments.details.view.DetailsFragmentModule
import com.example.presentation.ui.movie.fragments.list.view.MovieListFragment
import com.example.presentation.ui.movie.fragments.list.view.MovieListFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [BaseActivityModule::class]
)
abstract class MovieActivityModule {

    @Binds
    @PerActivity
    abstract fun appCompatActivity(movieActivity: MovieActivity): AppCompatActivity

    @PerFragment
    @ContributesAndroidInjector(modules = [MovieListFragmentModule::class])
    abstract fun movieFragmentInjector(): MovieListFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [DetailsFragmentModule::class])
    abstract fun detailsFragmentInjector(): DetailsFragment
}