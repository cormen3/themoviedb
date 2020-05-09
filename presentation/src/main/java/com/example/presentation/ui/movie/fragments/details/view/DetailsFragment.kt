package com.example.presentation.ui.movie.fragments.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.viewmodel.ViewModelProviderFactory
import com.example.domain.entity.movie.MovieInfoObject
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.common.extension.load
import com.example.presentation.common.extension.observe
import com.example.presentation.common.extension.viewModelProvider
import com.example.presentation.ui.movie.fragments.details.viewmodel.DetailsViewModel
import com.example.presentation.ui.movie.fragments.list.view.MovieListFragment.Companion.MOVIE_ID
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.layout_movie_detail_header.*
import kotlinx.android.synthetic.main.layout_movie_detail_top_banner.*
import javax.inject.Inject

class DetailsFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(factory)
        observe(viewModel.messageObservable, ::showMessage)
        observe(viewModel.movieInfo, ::updateInfo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        arguments?.getInt(MOVIE_ID)?.let {
            viewModel.movieId.value = it
            viewModel.getMovieDetails()
        }
    }

    private fun updateInfo(movieInfoObject: MovieInfoObject?) {
        movieDetailsPosterImage.load(
            movieDetailsPosterImage.context,
            movieInfoObject?.posterPath
        )
        movieDetailsBackdropImage.load(
            movieDetailsPosterImage.context,
            movieInfoObject?.backdropPath,
            R.drawable.img_banner_place_holder
        )
        movieDetailsPosterTitle.text = movieInfoObject?.title
        movieDetailsPosterDate.text = movieInfoObject?.releaseDate
        movieDetailsTitle.text = movieInfoObject?.title
        movieDetailsSummary.text = movieInfoObject?.overview
        movieDetailsStar.rating = 3.5f
        movieDetailsRelease.text =
            getString(R.string.release_date).plus(movieInfoObject?.releaseDate)
    }

    override fun onConnectionChanged(isConnected: Boolean) {
        viewModel.setConnection(isConnected)
    }
}
