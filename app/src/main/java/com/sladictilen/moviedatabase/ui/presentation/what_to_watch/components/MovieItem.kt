package com.sladictilen.moviedatabase.ui.presentation.what_to_watch.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieItem(
    title: String,
    year: String,
    genre: String,
    runtime: String,
    poster: String,
    imdbRating: String,
    rottentomatoRating: String
) {
    Card {
        // Poster
        Column(

        ) {

        }
    }

}

@Preview
@Composable
fun Prev() {
    MovieItem(
        title = "Deadpool",
        year = "2016",
        genre = "Action, Adventure, Comedy\"",
        runtime = "108 min",
        poster = "https://m.media-amazon.com/images/M/MV5BYzE5MjY1ZDgtMTkyNC00MTMyLThhMjAtZGI5OTE1NzFlZGJjXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
        imdbRating = "8.0",
        rottentomatoRating = "85%"
    )
}