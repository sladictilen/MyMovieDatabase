package com.sladictilen.moviedatabase.ui.presentation.discover

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.sladictilen.moviedatabase.data.apiOMDB.OmdbMovieResponse
import com.sladictilen.moviedatabase.data.apiOMDB.RatingsRepository
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
    private val savedStateHandle: SavedStateHandle,
    private val ratingsRepository: RatingsRepository
) : ViewModel() {
    var featuredWeeklyMovies by mutableStateOf(listOf<TrendingWeeklyMovies>())
    var popularMovies by mutableStateOf(listOf<PopularMovie>())

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
                    val movieRatings = ratingsRepository.getRatings(movie.data!!.imdb_id)
                    var imdbRating = 0.0
                    var tomatoRating = "0"

                    when (movieRatings) {
                        is Resource.Success -> {
                            val response = movieRatings.data!!
                            imdbRating = response.imdbRating.toDouble()
                            val ratings = response.Ratings
                            for (x in ratings) {
                                if (x.Source == "Rotten Tomatoes") {
                                    tomatoRating = x.Value
                                    break
                                }
                            }
                        }
                        is Resource.Error -> {
                            Log.d("Info", "${movie.message}")

                        }
                        is Resource.Loading -> {
                            /* todo */
                        }
                    }

                    localRepository.addToWatchList(
                        toWatchData = ToWatchData(
                            id_movie = movie.data.id,
                            runtime = movie.data.runtime,
                            year = movie.data.release_date.substring(0, 4),
                            posterUrl = movie.data.poster_path,
                            title = movie.data.title,
                            genre = genresToText(movie.data.genres),
                            tomatoRating = tomatoRating,
                            imdbRating = imdbRating
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
        viewModelScope.launch(Dispatchers.IO) {
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