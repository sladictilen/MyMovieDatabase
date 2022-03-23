package com.sladictilen.moviedatabase.ui.presentation.watchlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.watchlist.components.MovieItem

@Composable
fun WatchListScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "WATCH LIST", fontSize = 20.sp)
            }
            Column(verticalArrangement = Arrangement.Center) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sort),
                        contentDescription = "Sort button",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
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


}