package com.sladictilen.moviedatabase.ui.presentation.watchlist.components

import androidx.compose.runtime.Immutable

@Immutable
data class MovieItemModel(
    val title: String,
    val year: Int,
    val genre: String,
    val runtime: Int,
    val poster: String,
    val imdbRating: Double,
    val tomatoRating: Int
)