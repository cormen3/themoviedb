package com.example.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.common.extensions.orZero
import com.example.presentation.common.connection.ConnectionManager
import com.example.presentation.common.connection.ConnectionStatusCallback
import com.example.presentation.common.extension.toast
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(), ConnectionStatusCallback, MessagePresenter {

    @Inject
    lateinit var connectionManager: ConnectionManager

    @Inject
    protected lateinit var activityContext: Context

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun showMessage(@StringRes resourceId: Int) {
        showMessage(getString(resourceId))
    }

    override fun showMessage(message: String) {
        requireContext().toast(message)
    }

    override fun showMessage(message: MessageData) {
        if (message.message != null) {
            showMessage(message.message.orEmpty())
        } else if (message.resource != null) {
            showMessage(message.resource.orZero())
        }
    }

    override fun onConnectionChanged(isConnected: Boolean) {}
}
