package com.example.presentation.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.presentation.common.di.PerChildFragment
import com.example.presentation.common.di.PerFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

@Module
abstract class BaseFragmentModule {

    @Module
    companion object {
        const val FRAGMENT = "BaseFragmentModule.fragment"
        const val CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager"

        @JvmStatic
        @Provides
        @Named(CHILD_FRAGMENT_MANAGER)
        @PerFragment
        fun childFragmentManager(@Named(FRAGMENT) fragment: Fragment): FragmentManager =
            fragment.childFragmentManager

    }

}