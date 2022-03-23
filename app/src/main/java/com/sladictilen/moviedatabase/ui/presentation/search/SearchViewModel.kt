package com.sladictilen.moviedatabase.ui.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sladictilen.moviedatabase.navigation.Screens
import com.sladictilen.moviedatabase.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    // TODO repositories
) : ViewModel() {
    var searchText by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchValueChange -> {
                searchText = event.searchText
            }
            is SearchEvent.OnClearSearchClick -> {
                searchText = ""
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