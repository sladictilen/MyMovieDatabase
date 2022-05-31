package com.sladictilen.moviedatabase.ui.presentation.watchedmovies

import com.sladictilen.moviedatabase.data.database.WatchedData

sealed class WatchedMoviesEvent {
    data class OnSaveClick(val newRating: String, val newWatchDate: String) : WatchedMoviesEvent()
    data class OpenEditDialog(val movie: WatchedData) : WatchedMoviesEvent()
    data class OnRemoveWatchedMovieClick(val movie: WatchedData) : WatchedMoviesEvent()
    data class OnWatchedMovieClick(val idMovie: Int) : WatchedMoviesEvent()
}
