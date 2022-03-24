package com.sladictilen.moviedatabase.data.api

data class MoviesSearchResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)