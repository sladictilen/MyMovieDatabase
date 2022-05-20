package com.sladictilen.moviedatabase.ui.presentation.watchlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.ui.presentation.watchlist.WatchListEvent
import com.sladictilen.moviedatabase.ui.presentation.watchlist.WatchListViewModel

@Composable
fun AddToWatchedDialog(
    viewModel: WatchListViewModel = hiltViewModel()
) {
    if (viewModel.showDialog.value) {
        Dialog(onDismissRequest = { viewModel.showDialog.value = false }) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.DarkGray)
                    .padding(10.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "Add to watched", fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    Text(text = "Movie ${viewModel.selectedMovieTitle.value} will be marked as watched.")
                }
                Row(modifier = Modifier.padding(top = 5.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "Give this movie your rating!", fontWeight = FontWeight.Light)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    RatingPicker(
                        selectedRating = viewModel.userRating.value,
                        onRatingClick = { viewModel.userRating.value = it })
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column() {
                        Button(onClick = {
                            viewModel.onEvent(
                                WatchListEvent.OnAddToWatchedConfirmed(
                                    viewModel.selectedMovieId.value
                                )
                            )
                            viewModel.showDialog.value = false
                        }) {
                            Text(text = "Confirm")
                        }
                    }
                    Column() {
                        Button(onClick = { viewModel.showDialog.value = false }) {
                            Text(text = "Cancel")
                        }
                    }

                }

            }
        }
    }

}