package com.example.presentation.base.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.NetworkState
import com.example.common.RecyclerItem
import com.example.presentation.R

abstract class BaseAdapter(
    vararg types: Cell<RecyclerItem>,
    private val retryCallback: () -> Unit,
    private val listener: AdapterListener? = null
) : PagedListAdapter<RecyclerItem, RecyclerView.ViewHolder>(BASE_DIFF_CALLBACK) {

    private val cellTypes: CellTypes<RecyclerItem> = CellTypes(*types)
    private var networkState: NetworkState? = null

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            val item = getItem(position)
            cellTypes.of(item).type()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.network_state_item -> {
                cellTypes.of(viewType).networkHolder(parent, retryCallback)
            }
            else -> {
                cellTypes.of(viewType).holder(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.network_state_item -> {
                (holder as NetworkStateItemViewHolder).bindTo(networkState)
            }

            else -> {
                val item = getItem(position)
                cellTypes.of(item).bind(holder, item, listener)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        onBindViewHolder(holder, position)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

}
