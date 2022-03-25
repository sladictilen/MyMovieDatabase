package com.sladictilen.moviedatabase.ui.presentation.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.data.api.MoviesRepository
import com.sladictilen.moviedatabase.data.api.Result
import com.sladictilen.moviedatabase.navigation.Screens
import com.sladictilen.moviedatabase.util.Resource
import com.sladictilen.moviedatabase.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
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

    private var timer = 500L

    init {

    }


    private fun startSearching() {
        if (searchText.length >= 3) {
            viewModelScope.launch {
                delay(timer)
                val result = repository.searchMovies(searchText, 1)
                when (result) {
                    is Resource.Success -> {
                        searchResults = result.data?.results!!
                        cachedSearchResults = searchResults
                        Log.d("Info", "Accessing API")
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
                timer = 500L
                startSearching()
            }
            is SearchEvent.OnClearSearchClick -> {
                searchText = ""
                cachedSearch = ""
                searchResults = listOf()
                cachedSearchResults = listOf()
            }
            is SearchEvent.OnSearchedItemClick -> {
                sendUiEvent(UiEvent.Navigate(Screens.MovieProfile.route + "?title=${event.title}"))
            }
        }
    }

    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}