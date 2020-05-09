package com.example.domain.entity.movie

import com.example.common.AdapterClick
import com.example.common.RecyclerItem

data class MovieObject(
    var popularity: Float?,
    var voteCount: Int?,
    var video: Boolean?,
    var posterPath: String?,
    var movieId: Int?,
    var adult: Boolean?,
    var backdropPath: String?,
    var originalLanguage: String?,
    var originalTitle: String?,
    var genreIds: List<Int>?,
    var title: String?,
    var voteAverage: Float?,
    var overview: String?,
    var releaseDate: String?,
    override val id: String? =""
) : RecyclerItem, AdapterClick