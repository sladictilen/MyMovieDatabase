package com.sladictilen.moviedatabase.data.apiTMDB.featuredmovies

data class TrendingWeeklyMoviesResponse(
    val page: Int,
    val results: List<TrendingWeeklyMovies>,
    val total_pages: Int,
    val total_results: Int
)