package com.sladictilen.moviedatabase.ui.presentation.watchedmovies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepository
import com.sladictilen.moviedatabase.data.database.WatchedData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchedMoviesViewModel @Inject constructor(
    private val repository: LocalMoviesRepository
) : ViewModel() {
    var watchedMovies = repository.getWatched()
    var clickedMovie: WatchedData? = null
    var showEditDialog = mutableStateOf(false)

    fun onEvent(event: WatchedMoviesEvent) {
        when (event) {
            is WatchedMoviesEvent.OpenEditDialog -> {
                clickedMovie = event.movie
                showEditDialog.value = true
            }
            is WatchedMoviesEvent.OnSaveClick -> {
                val newMovieData = WatchedData(
                    id = clickedMovie!!.id,
                    dateWatched = event.newWatchDate,
                    userRating = event.newRating,
                    posterUrl = clickedMovie!!.posterUrl,
                    tomatoRating = clickedMovie!!.tomatoRating,
                    imdbRating = clickedMovie!!.imdbRating,
                    year = clickedMovie!!.year,
                    title = clickedMovie!!.title,
                    runtime = clickedMovie!!.runtime,
                    id_movie = clickedMovie!!.id_movie,
                    genre = clickedMovie!!.genre

                )
                viewModelScope.launch(Dispatchers.IO) {
                    repository.addToWatched(newMovieData)
                }
            }
            is WatchedMoviesEvent.OnRemoveWatchedMovieClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    repository.deleteWatchedMovie(event.movie)
                }

            }
        }
    }
}