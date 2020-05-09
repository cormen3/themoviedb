package com.example.data.entity.model.remote

import com.google.gson.annotations.SerializedName

class MovieVideos {
    @SerializedName("results")
    var videos: List<MovieVideo>? = null
}