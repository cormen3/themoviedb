package com.example.data.entity.model.remote

import com.google.gson.annotations.SerializedName

class Images(
    @SerializedName("base_url")
    var baseUrl: String?,
    @SerializedName("backdrop_sizes")
    var backdropSizes: List<String>?
)