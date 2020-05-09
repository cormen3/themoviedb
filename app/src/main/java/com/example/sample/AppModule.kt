package com.example.sample

import android.app.Application
import com.example.presentation.di.PerActivity
import com.example.presentation.ui.movie.MovieActivity
import com.example.presentation.ui.movie.MovieActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun application(application: App): Application

    @PerActivity
    @ContributesAndroidInjector(modules = [MovieActivityModule::class])
    abstract fun movieActivityInjector(): MovieActivity
}
