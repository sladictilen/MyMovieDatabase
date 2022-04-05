package com.sladictilen.moviedatabase.ui.presentation.watchlist

sealed class WatchListEvent {
    data class OnRemoveMovieClick(val id_movie: Int) : WatchListEvent()
    data class OnMarkAsWatchedClicked(val id_movie: Int) : WatchListEvent()
}
