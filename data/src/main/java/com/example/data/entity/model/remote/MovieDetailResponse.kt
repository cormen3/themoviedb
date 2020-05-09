package com.example.data.entity.model.remote

import com.google.gson.annotations.SerializedName

class MovieDetailResponse() {
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    @SerializedName("original_title")
    var originalTitle: String? = null
    @SerializedName("overview")
    var overview: String? = null
}