package com.sladictilen.moviedatabase.ui.presentation.watchlist.components

import androidx.lifecycle.ViewModel
import com.sladictilen.moviedatabase.data.api.MoviesRepository
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val localRepository: LocalMoviesRepository,
    val repository: MoviesRepository
) : ViewModel() {
    val watchList = localRepository.getWatchList()

    
}