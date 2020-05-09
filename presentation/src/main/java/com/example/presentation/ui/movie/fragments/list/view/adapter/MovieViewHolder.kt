package com.example.presentation.ui.movie.fragments.list.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.common.extensions.orZero
import com.example.domain.entity.movie.MovieObject
import com.example.presentation.common.extension.load
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: MovieObject) {
        itemView.itemMovieTitle.text = movie.title
        itemView.itemMovieRating.rating = movie.voteAverage?.orZero()?.div(2).orZero()
        itemView.itemMovieReleaseDate.text = movie.releaseDate.orEmpty()
        itemView.itemMovieOverview.text = movie.overview
        itemView.itemMovieImage.load(view.context, movie.posterPath)
    }
}
