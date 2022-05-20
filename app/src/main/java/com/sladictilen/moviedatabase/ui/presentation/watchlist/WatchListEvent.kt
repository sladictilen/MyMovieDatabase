package com.sladictilen.moviedatabase.ui.presentation.watchlist

sealed class WatchListEvent {
    data class OnRemoveMovieClick(val id_movie: Int) : WatchListEvent()
    data class OnMarkAsWatchedClicked(val id_movie: Int, val movieTitle: String) : WatchListEvent()
    data class OnAddToWatchedConfirmed(val id_movie: Int) : WatchListEvent()
}
