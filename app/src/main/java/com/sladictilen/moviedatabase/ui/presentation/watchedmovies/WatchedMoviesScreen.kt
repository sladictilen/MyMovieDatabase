package com.sladictilen.moviedatabase.ui.presentation.watchedmovies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.watchedmovies.components.WatchedMovieItem


@Composable
fun WatchedMoviesScreen(
    viewModel: WatchedMoviesViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "Watched", fontSize = 20.sp)
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
    }
    val watchedList = viewModel.watchedMovies.collectAsState(initial = emptyList())
    Row() {
        LazyColumn() {
            items(watchedList.value) {
                WatchedMovieItem(
                    posterUrl = it.posterUrl,
                    title = it.title,
                    genre = it.genre, //todo fix database
                    watchedDate = it.dateWatched,
                    userRating = it.userRating.toString()
                )
            }
        }
    }


}
