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
                    .height(300.dp)
                    .width(300.dp)
                    .background(Color.DarkGray)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Row() {
                    Text(text = "Add to watched", fontWeight = FontWeight.Bold)
                }
                Row() {
                    Text(text = "Movie ${viewModel.selectedMovieTitle.value} will be marked as watched.")
                }
                Row() {
                    Text(text = "Give this movie your rating!", fontWeight = FontWeight.ExtraLight)
                }
                Row() {

                }

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
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
                Row {
                    RatingPicker()
                }

            }
        }
    }

}