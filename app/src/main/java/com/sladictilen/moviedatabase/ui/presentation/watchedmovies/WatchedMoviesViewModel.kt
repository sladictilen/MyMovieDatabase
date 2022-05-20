package com.sladictilen.moviedatabase.ui.presentation.watchedmovies

import androidx.lifecycle.ViewModel
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepository
import javax.inject.Inject

class WatchedMoviesViewModel @Inject constructor(
    private val repository: LocalMoviesRepository
) : ViewModel() {
    val watchedMovies = repository.getWatched()
}