package com.sladictilen.moviedatabase.ui.presentation.discover

import androidx.lifecycle.ViewModel
import com.sladictilen.moviedatabase.data.api.MoviesRepository
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {
    val featuredMovies = null
}