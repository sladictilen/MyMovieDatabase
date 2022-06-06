package com.sladictilen.moviedatabase.ui.presentation.watchlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepository
import com.sladictilen.moviedatabase.data.database.WatchedData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository
) : ViewModel() {
    val watchList = localMoviesRepository.getWatchList()
    val showDialog = mutableStateOf(false)

    val selectedMovieId = mutableStateOf(0)
    val selectedMovieTitle = mutableStateOf("")
    val userRating = mutableStateOf("0")

    fun onEvent(event: WatchListEvent) {
        when (event) {
            is WatchListEvent.OnRemoveMovieClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val movie = localMoviesRepository.getMovieFromToWatchListById(event.id_movie)
                    if (movie != null) {
                        localMoviesRepository.deleteWatchListMovie(movie)
                    }
                }
            }
            is WatchListEvent.OnMarkAsWatchedClicked -> {
                showDialog.value = true
                selectedMovieTitle.value = event.movieTitle
                selectedMovieId.value = event.id_movie
            }
            is WatchListEvent.OnAddToWatchedConfirmed -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val movie = localMoviesRepository.getMovieFromToWatchListById(event.id_movie)
                    if (movie != null) {
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                        val formatted = current.format(formatter)

                        localMoviesRepository.addToWatched(
                            WatchedData(
                                title = movie.title,
                                genre = movie.genre,
                                dateWatched = formatted,
                                posterUrl = movie.posterUrl,
                                id_movie = movie.id_movie,
                                imdbRating = movie.imdbRating,
                                runtime = movie.runtime,
                                tomatoRating = movie.tomatoRating,
                                userRating = userRating.value,
                                year = movie.year
                            )
                        )
                    }
                    WatchListEvent.OnRemoveMovieClick(movie!!.id_movie)
                }
                viewModelScope.launch(Dispatchers.IO) {
                    val movie = localMoviesRepository.getMovieFromToWatchListById(event.id_movie)
                    if (movie != null) {
                        localMoviesRepository.deleteWatchListMovie(movie)
                    }
                }

            }
        }
    }

}