package com.sladictilen.moviedatabase.navigation

import com.sladictilen.moviedatabase.R

// Title and icon only needed for Bottom navigation bar
sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: Int
) {
    object DiscoverScreen : BottomNavScreens("discover", "Discover", R.drawable.ic_discover)
    object SearchScreen : BottomNavScreens("search", "Search", R.drawable.ic_search)
    object WatchListScreen : BottomNavScreens("watchlist", "Watch List", R.drawable.ic_view_show_all)
    object WatchedMovies :
        BottomNavScreens("watchedmovies", "Watched movies", R.drawable.ic_movie_media_player)
}
