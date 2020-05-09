package com.example.domain.entity.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.common.NetworkState
import com.example.common.RecyclerItem

data class MovieHolder<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<NetworkState>,
    val refreshState: LiveData<NetworkState>,
    val refresh: () -> Unit,
    val retry: () -> Unit,
    override val id: String? = ""
) : RecyclerItem
