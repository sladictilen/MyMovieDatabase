package com.sladictilen.moviedatabase.ui.presentation.discover

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.sladictilen.moviedatabase.data.apiTMDB.MoviesRepository
import com.sladictilen.moviedatabase.data.apiTMDB.featuredmovies.TrendingWeeklyMovies
import com.sladictilen.moviedatabase.data.apiTMDB.moviedetail.Genre
import com.sladictilen.moviedatabase.data.apiTMDB.popularmovies.PopularMovie
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepository
import com.sladictilen.moviedatabase.data.database.ToWatchData
import com.sladictilen.moviedatabase.navigation.Screens
import com.sladictilen.moviedatabase.util.Resource
import com.sladictilen.moviedatabase.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val localRepository: LocalMoviesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var featuredWeeklyMovies by mutableStateOf(listOf<TrendingWeeklyMovies>())
    var popularMovies by mutableStateOf(listOf<PopularMovie>())
    var toWatchList = localRepository.getWatchList()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {


        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTrendingWeeklyMovies()) {
                is Resource.Success -> {
                    featuredWeeklyMovies = result.data?.results!!
                }
                is Resource.Error -> {
                    Log.d("Error", result.message!!)
                }
                is Resource.Loading -> {
                    /* TODO */
                }
            }

        }

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPopularMovies()) {
                is Resource.Success -> {
                    popularMovies = result.data?.results!!
                }
                is Resource.Error -> {
                    Log.d("Error", result.message!!)
                }
                is Resource.Loading -> {
                    /* TODO */
                }
            }
        }

    }

    fun onEvent(event: DiscoverEvent) {
        when (event) {
            is DiscoverEvent.OnMovieClick -> {
                sendUiEvent(UiEvent.Navigate(Screens.MovieProfile.route + "?id=${event.id_movie}"))
            }
            is DiscoverEvent.OnAddToWatchListClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val movie = repository.getIdDetails(event.id_movie)
                    localRepository.addToWatchList(
                        toWatchData = ToWatchData(
                            id_movie = movie.data?.id!!,
                            runtime = movie.data.runtime,
                            year = movie.data.release_date.substring(0, 4),
                            posterUrl = movie.data.poster_path,
                            title = movie.data.title,
                            genre = genresToText(movie.data.genres),
                            tomatoRating = "55",
                            imdbRating = 7.6
                        )
                    )
                }
                sendUiEvent(
                    UiEvent.ShowSnackbar(
                        "Added to your To-Watch list!"
                    )
                )
            }
            is DiscoverEvent.OnRemoveFromToWatchListClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    localRepository.getMovieFromToWatchListById(savedStateHandle.get<Int>("id")!!)
                        ?.let { localRepository.deleteWatchListMovie(it) }
                }
                sendUiEvent(
                    UiEvent.ShowSnackbar(
                        "Removed from your To-Watch list!"
                    )
                )
            }
        }
    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private fun genresToText(genres: List<Genre>): String {
        var genre = ""
        genres.forEachIndexed { index, element ->
            genre += if (index != genres.size - 1) {
                "${element.name}, "
            } else {
                element.name
            }
        }
        return genre
    }

}