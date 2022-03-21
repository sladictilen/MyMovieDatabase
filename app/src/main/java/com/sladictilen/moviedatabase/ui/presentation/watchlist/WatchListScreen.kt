package com.sladictilen.moviedatabase.ui.presentation.watchlist

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sladictilen.moviedatabase.ui.presentation.discover.components.MovieItem

@Composable
fun WatchListScreen() {
    Row() {
        MovieItem(
            title = "Deadpool",
            year = "2016",
            genre = "Action, Adventure, Comedy",
            runtime = "108 min",
            poster = "https://m.media-amazon.com/images/M/MV5BYzE5MjY1ZDgtMTkyNC00MTMyLThhMjAtZGI5OTE1NzFlZGJjXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
            imdbRating = "8.0",
            rottentomatoRating = "85%"
        )
    }
}