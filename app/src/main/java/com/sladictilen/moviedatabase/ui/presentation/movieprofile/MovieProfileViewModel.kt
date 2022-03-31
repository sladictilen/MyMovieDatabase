package com.sladictilen.moviedatabase.ui.presentation.movieprofile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.data.api.MoviesRepository
import com.sladictilen.moviedatabase.data.api.cast.Cast
import com.sladictilen.moviedatabase.data.api.moviedetail.Genre
import com.sladictilen.moviedatabase.data.api.moviedetail.MovieDetailResponse
import com.sladictilen.moviedatabase.data.api.moviedetail.SpokenLanguage
import com.sladictilen.moviedatabase.data.api.similarmovies.SimilarMoviesData
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
    savedStateHandle: SavedStateHandle
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
    var watched by mutableStateOf("Not watched")
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

    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}