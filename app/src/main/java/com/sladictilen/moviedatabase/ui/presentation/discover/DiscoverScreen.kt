package com.sladictilen.moviedatabase.ui.presentation.discover

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import com.sladictilen.moviedatabase.ui.presentation.discover.components.MovieItem

@Composable
fun DiscoverScreen(){
    // Includes What To Watch and Trending movies
    NavigationBar() {

    }
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