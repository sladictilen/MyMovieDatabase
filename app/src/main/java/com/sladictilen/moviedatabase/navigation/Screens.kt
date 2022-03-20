package com.sladictilen.moviedatabase.navigation

import com.sladictilen.moviedatabase.R

sealed class Screens(val route: String, val icon: Int) {
    object DiscoverScreen : Screens("discover", R.drawable.ic_discover)
    object SearchScreen : Screens("search", R.drawable.ic_search)
    object WatchListScreen : Screens("watchlist", R.drawable.ic_view_show_all)
    object WatchedMovies : Screens("watchedmovies", R.drawable.ic_movie_media_player)
}
