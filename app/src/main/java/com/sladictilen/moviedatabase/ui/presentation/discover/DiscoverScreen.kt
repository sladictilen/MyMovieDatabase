package com.sladictilen.moviedatabase.ui.presentation.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.ui.presentation.discover.components.MoviePosterItem
import com.sladictilen.moviedatabase.util.UiEvent

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> {}
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Box() {
            Column() {
                Row(modifier = Modifier.padding(bottom = 10.dp)) {
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
                                MoviePosterItem(
                                    posterUrl = it.poster_path,
                                    isWatched = false,
                                    isOnWatchList = viewModel.isOnWatchList(it.id),
                                    onClick = { viewModel.onEvent(DiscoverEvent.OnMovieClick(it.id)) },
                                    onAddToWatchListClick = {
                                        viewModel.onEvent(
                                            DiscoverEvent.OnAddToWatchListClick(
                                                it.id
                                            )
                                        )
                                    },
                                    onRemoveFromToWatchListClick = {
                                        viewModel.onEvent(
                                            DiscoverEvent.OnRemoveFromToWatchListClick(
                                                it.id
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

        }
        Divider()


    }

}