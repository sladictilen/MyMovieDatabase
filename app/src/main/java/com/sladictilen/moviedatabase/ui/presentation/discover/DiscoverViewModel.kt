package com.sladictilen.moviedatabase.ui.presentation.discover

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.data.api.MoviesRepository
import com.sladictilen.moviedatabase.data.api.featuredmovies.TrendingWeeklyMovies
import com.sladictilen.moviedatabase.data.api.featuredmovies.TrendingWeeklyMoviesResponse
import com.sladictilen.moviedatabase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {
    var featuredWeeklyMovies by mutableStateOf(listOf<TrendingWeeklyMovies>())

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

    }
}