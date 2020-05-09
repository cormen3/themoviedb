package com.example.presentation.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.common.NetworkState
import com.example.presentation.R
import com.example.common.RecyclerItem

object NetworkCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is NetworkState
    }

    override fun type(): Int {
        return R.layout.network_state_item
    }

    override fun networkHolder(parent: ViewGroup, retryCallback: () -> Unit): RecyclerView.ViewHolder {
        return NetworkStateItemViewHolder(
            parent.viewOf(type()),
            retryCallback
        )
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        listener: AdapterListener?
    ) {
        if (holder is NetworkStateItemViewHolder && item is NetworkState) {
            holder.bindTo(item)
            holder.itemView.setOnClickListener {
                listener?.listen(item)
            }
        }
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        throw Exception()
    }

}