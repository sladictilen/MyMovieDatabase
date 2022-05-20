package com.sladictilen.moviedatabase.ui.presentation.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository
) : ViewModel() {
    val watchList = localMoviesRepository.getWatchList()

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
                // TODO Add custom dialog to ask user to rate a movie and pick a watch date (else picks today date)
                /*
                viewModelScope.launch(Dispatchers.IO){
                    val movie = localMoviesRepository.getMovieFromWatchedListById(event.id_movie)
                    if(movie != null){
                        localMoviesRepository.addToWatched(movie)
                    }
                } */
            }
        }
    }

}