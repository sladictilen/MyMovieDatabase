package com.sladictilen.moviedatabase.ui.presentation.watchedmovies

import androidx.lifecycle.ViewModel
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchedMoviesViewModel @Inject constructor(
    repository: LocalMoviesRepository
) : ViewModel() {
    val watchedMovies = repository.getWatched()
}