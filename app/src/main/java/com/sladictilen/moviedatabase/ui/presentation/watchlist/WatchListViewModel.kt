package com.sladictilen.moviedatabase.ui.presentation.watchlist

import androidx.lifecycle.ViewModel
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository
) : ViewModel() {
    val watchList = localMoviesRepository.getWatchList()
}