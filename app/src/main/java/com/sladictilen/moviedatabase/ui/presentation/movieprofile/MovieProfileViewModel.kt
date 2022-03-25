package com.sladictilen.moviedatabase.ui.presentation.movieprofile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.data.api.MoviesRepository
import com.sladictilen.moviedatabase.data.api.moviedetail.MovieDetailResponse
import com.sladictilen.moviedatabase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieProfileViewModel @Inject constructor(
    val repository: MoviesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    lateinit var movieDetails: MovieDetailResponse

    var title by mutableStateOf("")
        private set
    var overview by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            val result = repository.getIdDetails(savedStateHandle.get<Int>("id")!!)
            Log.d("info" , "result: $result")
            when (result) {
                is Resource.Success -> {
                    movieDetails = result.data!!
                    title = movieDetails.title
                    overview = movieDetails.overview
                    Log.d("Info", "Got movie data")
                }
                is Resource.Error -> {
                    Log.d("Info", "Error getting movie data")
                }
            }
        }

    }
}