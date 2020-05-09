package com.example.presentation.ui.movie.fragments.list.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.common.RecyclerItem
import com.example.domain.entity.movie.MovieObject
import com.example.presentation.R
import com.example.presentation.base.adapter.AdapterListener
import com.example.presentation.base.adapter.Cell

object MovieCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is MovieObject
    }

    override fun type(): Int {
        return R.layout.item_movie
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MovieViewHolder(
            parent.viewOf(type())
        )
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        listener: AdapterListener?
    ) {
        if (holder is MovieViewHolder && item is MovieObject) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                listener?.listen(item)
            }
        }
    }

    override fun networkHolder(
        parent: ViewGroup,
        retryCallback: () -> Unit
    ): RecyclerView.ViewHolder {
        throw Exception()
    }

}