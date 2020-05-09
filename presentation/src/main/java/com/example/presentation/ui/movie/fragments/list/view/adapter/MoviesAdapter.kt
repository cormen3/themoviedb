package com.example.presentation.ui.movie.fragments.list.view.adapter

import com.example.presentation.base.adapter.AdapterListener
import com.example.presentation.base.adapter.BaseAdapter
import com.example.presentation.base.adapter.NetworkCell

class MoviesAdapter(listener: AdapterListener, retryCallback: () -> Unit) : BaseAdapter(
    MovieCell,
    NetworkCell,
    listener = listener,
    retryCallback = retryCallback
)