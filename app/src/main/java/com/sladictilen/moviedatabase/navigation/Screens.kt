package com.sladictilen.moviedatabase.navigation

import com.sladictilen.moviedatabase.R

sealed class Screens(
    val route: String,
    val title: String,
    val icon: Int
) {
    object DiscoverScreen : Screens("discover", "Discover", R.drawable.ic_discover)
    object SearchScreen : Screens("search", "Search", R.drawable.ic_search)
    object WatchListScreen : Screens("watchlist", "Watch List", R.drawable.ic_view_show_all)
    object WatchedMovies : Screens("watchedmovies", "Watched movies", R.drawable.ic_movie_media_player)
}
