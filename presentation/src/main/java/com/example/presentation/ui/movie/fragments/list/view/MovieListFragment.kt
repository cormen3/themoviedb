package com.example.presentation.ui.movie.fragments.list.view

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.AdapterClick
import com.example.common.NetworkState
import com.example.common.RecyclerItem
import com.example.common.viewmodel.ViewModelProviderFactory
import com.example.domain.entity.movie.MovieObject
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.base.adapter.AdapterListener
import com.example.presentation.common.extension.observe
import com.example.presentation.common.extension.viewModelProvider
import com.example.presentation.ui.movie.fragments.list.view.adapter.MoviesAdapter
import com.example.presentation.ui.movie.fragments.list.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

class MovieListFragment : BaseFragment(), AdapterListener {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: MovieListViewModel
    var mBundleRecyclerViewState: Bundle? = null

    private val moviesAdapter by lazy {
        MoviesAdapter(this, viewModel::retry)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(factory)
        observe(viewModel.messageObservable, ::showMessage)
        observe(viewModel.items, ::updateList)
        observe(viewModel.networkState, moviesAdapter::setNetworkState)
        observe(viewModel.refreshState, ::setRefreshState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_list, container, false)

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupList()

        movieListSwipeRefreshLayout.setOnRefreshListener { viewModel.refresh() }
    }

    private fun setupList() {
        moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(moviesRecyclerView.context)
            adapter = moviesAdapter
        }
    }

    private fun updateList(it: PagedList<RecyclerItem>?) {
        viewModel.movies = it
        moviesAdapter.submitList(viewModel.movies)
    }

    private fun setRefreshState(newNetworkState: NetworkState?) {
        if (newNetworkState == NetworkState.LOADED)
            movieListSwipeRefreshLayout.isRefreshing = false
    }

    override fun onConnectionChanged(isConnected: Boolean) {
        viewModel.setConnection(isConnected)
    }

    private fun saveScrollPosition() {
        mBundleRecyclerViewState = Bundle()
        val listState = moviesRecyclerView.layoutManager?.onSaveInstanceState()
        mBundleRecyclerViewState?.putParcelable(KEY_RECYCLER_STATE, listState)
    }

    private fun restoreScrollPosition() {
        mBundleRecyclerViewState?.let {
            val listState = it.getParcelable<Parcelable>(KEY_RECYCLER_STATE)
            moviesRecyclerView.layoutManager?.onRestoreInstanceState(listState)
        }
    }

    override fun onPause() {
        super.onPause()
        saveScrollPosition()
    }

    override fun onResume() {
        super.onResume()
        restoreScrollPosition()
    }

    override fun listen(click: AdapterClick?) {
        when (click) {
            is MovieObject -> {
                val bundle = bundleOf(MOVIE_ID to click.movieId)
                findNavController().navigate(R.id.detailsFragment, bundle)
            }
        }
    }

    companion object {
        var KEY_RECYCLER_STATE = "KEY_RECYCLER_STATE"
        var MOVIE_ID = "movieId"
    }
}
