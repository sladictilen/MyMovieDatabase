package com.sladictilen.moviedatabase.data.apiTMDB.popularmovies

data class PopularMoviesResponse(
    val page: Int,
    val results: List<PopularMovie>,
    val total_pages: Int,
    val total_results: Int
)