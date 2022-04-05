package com.sladictilen.moviedatabase.ui.presentation.watchlist.components

sealed class WatchListEvent {
    data class OnRemoveMovieClick(val id_movie: Int)
    data class OnMarkAsWatchedClicked(val id_movie: Int)
}
