package com.sladictilen.moviedatabase.ui.presentation.movieprofile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.data.api.MoviesRepository
import com.sladictilen.moviedatabase.data.api.cast.Cast
import com.sladictilen.moviedatabase.data.api.moviedetail.Genre
import com.sladictilen.moviedatabase.data.api.moviedetail.MovieDetailResponse
import com.sladictilen.moviedatabase.data.api.moviedetail.SpokenLanguage
import com.sladictilen.moviedatabase.data.api.similarmovies.SimilarMoviesData
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
class MovieProfileViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val localRepository: LocalMoviesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    lateinit var movieDetails: MovieDetailResponse


    var title by mutableStateOf("")
        private set
    var overview by mutableStateOf("")
        private set
    var posterUrl by mutableStateOf("")
        private set
    var releaseDate by mutableStateOf("")
        private set
    var runtime by mutableStateOf(0)
        private set
    var status by mutableStateOf("")
        private set
    var spokenLanguages by mutableStateOf(listOf<SpokenLanguage>())
        private set

    // Watched status
    var watched by mutableStateOf("Not watched")
        private set
    var watchedColor by mutableStateOf(Color.Red)
        private set
    var isOnWatchList by mutableStateOf(false)
        private set
    var isWatched by mutableStateOf(false)
        private set

    var youtubeTrailerURL by mutableStateOf("")
        private set


    var cast by mutableStateOf(listOf<Cast>())
        private set
    var similarMovies by mutableStateOf(listOf<SimilarMoviesData>())
        private set
    var genre by mutableStateOf("")
        private set
    var tagline by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getIdDetails(savedStateHandle.get<Int>("id")!!)
            when (result) {
                is Resource.Success -> {
                    movieDetails = result.data!!
                    title = movieDetails.title
                    overview = movieDetails.overview
                    posterUrl = movieDetails.poster_path
                    releaseDate = movieDetails.release_date
                    runtime = movieDetails.runtime
                    status = movieDetails.status
                    spokenLanguages = movieDetails.spoken_languages
                    tagline = movieDetails.tagline

                    genresToText(movieDetails.genres)

                    setWatchStatus(savedStateHandle.get<Int>("id")!!)

                    Log.d("Info", "Got movie data")
                }
                is Resource.Error -> {
                    Log.d("Info", "Error getting movie data")
                }
                is Resource.Loading -> {
                    /* TODO loading */
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getMovieCast(savedStateHandle.get<Int>("id")!!)
            when (result) {
                is Resource.Success -> {
                    cast = result.data?.cast!!
                }
                is Resource.Error -> {
                    Log.d("Info", "Error getting movie cast.")
                }
                is Resource.Loading -> {
                    /* TODO loading */
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getSimilarMovies(savedStateHandle.get<Int>("id")!!)
            when (result) {
                is Resource.Success -> {
                    similarMovies = result.data?.results!!
                }
                is Resource.Error -> {
                    Log.d("Info", "Error getting similar movies.")
                }
                is Resource.Loading -> {
                    /* TODO loading */
                }
            }
        }
    }

    fun onEvent(event: MovieProfileEvent) {
        when (event) {
            is MovieProfileEvent.OnSimilarMovieClick -> {
                sendUiEvent(UiEvent.Navigate(Screens.MovieProfile.route + "?id=${event.id}"))
            }
            is MovieProfileEvent.OnBackPressed -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is MovieProfileEvent.OnAddToWatchListButtonClick -> {
                if (!isOnWatchList) {
                    viewModelScope.launch(Dispatchers.IO) {
                        localRepository.addToWatchList(
                            /* TODO connect with imdb rating and tomato */
                            toWatchData =
                            ToWatchData(
                                title = title,
                                genre = genre,
                                id_movie = savedStateHandle.get<Int>("id")!!,
                                imdbRating = 6.6,
                                tomatoRating = 88,
                                posterUrl = posterUrl,
                                year = releaseDate.substring(0, 4),
                                runtime = runtime
                            )
                        )
                        isOnWatchList = true
                        setWatchStatus(savedStateHandle.get<Int>("id")!!)
                        sendUiEvent(UiEvent.ShowSnackbar("$title added to your To-Watch list!"))
                    }
                } else {
                    isOnWatchList = false
                    viewModelScope.launch(Dispatchers.IO) {
                        localRepository.getMovieFromToWatchListById(savedStateHandle.get<Int>("id")!!)
                            ?.let { localRepository.deleteWatchListMovie(it) }
                        setWatchStatus(savedStateHandle.get<Int>("id")!!)
                    }
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            "$title removed from your To-Watch list!"
                        )
                    )

                }
            }
            is MovieProfileEvent.OnAddToWatchedListButtonClick -> {
                /* TODO */
            }
        }
    }


    private suspend fun setWatchStatus(id: Int) {
        if (localRepository.getMovieFromToWatchListById(id) != null) {
            watched = "On your To-Watch List"
            watchedColor = Color.Yellow
            isOnWatchList = true
        } else {
            if (localRepository.getMovieFromWatchedListById(id) == null) {
                watched = "Not watched"
                watchedColor = Color.Red
                isWatched = false
            } else {
                watched = "Watched"
                watchedColor = Color.Green
                isWatched = true
                isOnWatchList = false
            }
        }


    }

    private fun genresToText(genres: List<Genre>) {
        genres.forEachIndexed { index, element ->
            genre += if (index != genres.size - 1) {
                "${element.name}, "
            } else {
                element.name
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}