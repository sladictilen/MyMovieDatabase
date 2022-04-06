package com.sladictilen.moviedatabase.ui.presentation.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.ui.presentation.discover.components.MoviePosterItem

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel()
) {
    // Includes What To Watch and Trending movies
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Box() {
            Column() {
                Row() {
                    Text(
                        text = "Trending movies",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                }
                Row() {
                    LazyRow() {
                        items(viewModel.featuredWeeklyMovies) {
                            Column(Modifier.padding(end = 10.dp)) {
                                MoviePosterItem(posterUrl = it.poster_path, movie_id = it.id)
                            }
                        }
                    }
                }
            }

        }
        Divider()


    }

}