package com.sladictilen.moviedatabase.ui.presentation.discover

sealed class DiscoverEvent {
    data class OnMovieClick(val id_movie: Int) : DiscoverEvent()
    data class OnAddToWatchListClick(val id_movie: Int) : DiscoverEvent()
    data class OnRemoveFromToWatchListClick(val id_movie: Int) : DiscoverEvent()
}
