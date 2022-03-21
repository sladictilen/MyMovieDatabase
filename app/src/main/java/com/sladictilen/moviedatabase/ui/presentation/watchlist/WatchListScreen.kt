package com.sladictilen.moviedatabase.ui.presentation.watchlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.discover.components.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchListScreen() {

    Column(modifier = Modifier.fillMaxWidth()) {
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


}