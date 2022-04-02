package com.sladictilen.moviedatabase.ui.presentation.movieprofile

sealed class MovieProfileEvent {
    data class OnSimilarMovieClick(val id: Int) : MovieProfileEvent()
    object OnBackPressed : MovieProfileEvent()
    object OnAddToWatchListButtonClick : MovieProfileEvent()
    object OnAddToWatchedListButtonClick : MovieProfileEvent()
}
