package com.example.presentation.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.common.NetworkState
import com.example.common.Status
import com.example.presentation.R
import com.example.presentation.common.extension.goneOrVisible

class NetworkStateItemViewHolder( view: View,  private val retryCallback: () -> Unit) : RecyclerView.ViewHolder(view) {
    private val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
    private val retry = view.findViewById<Button>(R.id.retry_button)
    private val errorMsg = view.findViewById<TextView>(R.id.error_msg)
    init {
        retry.setOnClickListener {
            retryCallback()
        }
    }
    fun bindTo(networkState: NetworkState?) {
        progressBar.goneOrVisible(networkState?.status == Status.RUNNING)
        retry.goneOrVisible(networkState?.status == Status.FAILED)
        errorMsg.goneOrVisible(networkState?.msg != null)
        errorMsg.text = networkState?.msg
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
            return NetworkStateItemViewHolder(
                view,
                retryCallback
            )
        }
    }
}
