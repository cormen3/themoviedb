package com.example.data.entity.model.remote

import com.google.gson.annotations.SerializedName

class Movie (
    var popularity: Float?,
    @SerializedName("vote_count")
    var voteCount: Int?,
    var video: Boolean?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("id")
    var movieId: Int?,
    var adult: Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("genre_ids")
    var genreIds: List<Int>?,
    var title: String?,
    @SerializedName("vote_average")
    var voteAverage: Float?,
    var overview: String?,
    @SerializedName("release_date")
    var releaseDate: String?
)
