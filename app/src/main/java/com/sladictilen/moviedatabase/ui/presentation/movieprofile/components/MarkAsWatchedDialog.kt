package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.MovieProfileEvent
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.MovieProfileViewModel
import com.sladictilen.moviedatabase.util.components.EditDateComponent
import com.sladictilen.moviedatabase.util.components.RatingPicker

@Composable
fun MarkAsWatchedDialog(viewModel: MovieProfileViewModel = hiltViewModel()) {

    if (viewModel.showDialog.value) {
        Dialog(onDismissRequest = { viewModel.showDialog.value = false }) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.DarkGray)
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Mark as watched", fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    Text(text = "Movie ${viewModel.title} will be marked as watched.")
                }
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Select date you watched this movie.",
                        fontWeight = FontWeight.Light
                    )
                }
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    EditDateComponent(date = viewModel.selectedDate.value,
                        onChange = {
                            viewModel.selectedDate.value = it
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Give it your rating.", fontWeight = FontWeight.Light)
                }
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    RatingPicker(
                        selectedRating = viewModel.selectedRating.value,
                        onRatingClick = { viewModel.selectedRating.value = it })
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column() {
                        Button(onClick = {
                            viewModel.onEvent(MovieProfileEvent.OnConfirmAddToWatchedListButtonClick)
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