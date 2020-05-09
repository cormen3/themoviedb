package com.example.data.entity.model.remote

class MoviesResponse constructor(
    var page: Int?,
    var total_results: Int?,
    var total_pages: Int?,
    var results: List<Movie>?
)