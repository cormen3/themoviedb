package com.example.data.entity.model.remote

import com.google.gson.annotations.SerializedName

class MovieReviewsResponse(
    @SerializedName("results")
    var reviews: List<MovieReview>? = null
)
