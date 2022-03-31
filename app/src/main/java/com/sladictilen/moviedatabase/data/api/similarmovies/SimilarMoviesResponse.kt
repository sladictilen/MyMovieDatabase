package com.sladictilen.moviedatabase.data.api.similarmovies

data class SimilarMoviesResponse(
    val page: Int,
    val results: List<SimilarMoviesData>,
    val total_pages: Int,
    val total_results: Int
)