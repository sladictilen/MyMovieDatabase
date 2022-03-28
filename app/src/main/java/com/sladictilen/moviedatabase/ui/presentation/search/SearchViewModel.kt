package com.sladictilen.moviedatabase.ui.presentation.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.data.api.MoviesRepository
import com.sladictilen.moviedatabase.data.api.moviesearch.Result
import com.sladictilen.moviedatabase.navigation.Screens
import com.sladictilen.moviedatabase.util.Resource
import com.sladictilen.moviedatabase.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    var searchText by mutableStateOf("")
        private set

    var searchResults by mutableStateOf<List<Result>>(listOf())
        private set

    var searching = false

    var cachedSearch = ""
    var cachedSearchResults = listOf<Result>()


    private var job: Job? = null

    init {

    }


    private fun startSearching() {
        // We cancel previous job
        job?.cancel("Change happened.")
        if (searchText.length >= 3) {
            job = viewModelScope.launch(Dispatchers.IO) {
                delay(500L)
                when (val result = repository.searchMovies(searchText, 1)) {
                    is Resource.Success -> {
                        searchResults = result.data?.results!!
                        cachedSearchResults = searchResults
                        Log.d("Info", "Accessing API")
                    }
                    is Resource.Error -> {
                        Log.d("Info", "Accessing API")
                    }
                    is Resource.Loading -> {
                        /* TODO */
                    }
                }
            }
        }
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchValueChange -> {
                searchText = event.searchText
                cachedSearch = searchText
                //canceling so we don't put more "jobs" in queue
                startSearching()
            }
            is SearchEvent.OnClearSearchClick -> {
                searchText = ""
                cachedSearch = ""
                searchResults = listOf()
                cachedSearchResults = listOf()
            }
            is SearchEvent.OnSearchedItemClick -> {
                sendUiEvent(UiEvent.Navigate(Screens.MovieProfile.route + "?id=${event.id}"))
            }
        }
    }

    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}