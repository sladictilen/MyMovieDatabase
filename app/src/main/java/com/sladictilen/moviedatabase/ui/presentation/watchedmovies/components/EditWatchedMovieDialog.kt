package com.sladictilen.moviedatabase.ui.presentation.watchedmovies.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.watchedmovies.WatchedMoviesEvent
import com.sladictilen.moviedatabase.ui.presentation.watchedmovies.WatchedMoviesViewModel
import com.sladictilen.moviedatabase.ui.presentation.watchlist.components.RatingPicker

enum class SelectedItem {
    ChangeWatchDate,
    ChangeMovieRating,
    RemoveMovie,
    None
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditWatchedMovieDialog(
    viewModel: WatchedMoviesViewModel = hiltViewModel()
) {

    // TODO make it so when you click on
    //  something dialog changes fully (maybe write over)
    if (viewModel.showEditDialog.value) {
        var editDateVisibility by remember { mutableStateOf(false) }
        var editRatingVisibility by remember { mutableStateOf(false) }
        var removeVisibility by remember { mutableStateOf(false) }
        var selectedItem by remember { mutableStateOf(SelectedItem.None) }
        Dialog(
            onDismissRequest = { viewModel.showEditDialog.value = false },
            properties = DialogProperties(usePlatformDefaultWidth = false) // This allows AnimatedVisibility to resize dialog
        ) {
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
                    Text(text = viewModel.clickedMovie!!.title, fontWeight = FontWeight.Bold)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    Text(text = "What would you like to do?")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            editRatingVisibility = false
                            removeVisibility = false
                            editDateVisibility = !editDateVisibility
                            selectedItem = if (selectedItem == SelectedItem.ChangeWatchDate) {
                                SelectedItem.None
                            } else {
                                SelectedItem.ChangeWatchDate
                            }

                        }
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(visible = selectedItem == SelectedItem.ChangeWatchDate) {
                        Column(modifier = Modifier.padding(end = 3.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_selected_arrow_right),
                                contentDescription = null,
                                tint = MaterialTheme.colors.primary,
                                modifier = Modifier.size(10.dp)
                            )

                        }
                    }
                    Column {
                        Text(text = "Change watched date.", fontWeight = FontWeight.Light)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            editDateVisibility = false
                            removeVisibility = false
                            editRatingVisibility = !editRatingVisibility
                            selectedItem = if (selectedItem == SelectedItem.ChangeMovieRating) {
                                SelectedItem.None
                            } else {
                                SelectedItem.ChangeMovieRating
                            }
                        }
                        .padding(top = 5.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AnimatedVisibility(visible = selectedItem == SelectedItem.ChangeMovieRating) {
                        Column(modifier = Modifier.padding(end = 3.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_selected_arrow_right),
                                contentDescription = null,
                                tint = MaterialTheme.colors.primary,
                                modifier = Modifier.size(10.dp)
                            )

                        }
                    }
                    Column {
                        Text(text = "Change my movie rating.", fontWeight = FontWeight.Light)
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            editRatingVisibility = false
                            editDateVisibility = false
                            removeVisibility = !removeVisibility
                            selectedItem = if (selectedItem == SelectedItem.RemoveMovie) {
                                SelectedItem.None
                            } else {
                                SelectedItem.RemoveMovie
                            }

                        }
                        .padding(bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(visible = selectedItem == SelectedItem.RemoveMovie) {
                        Column(modifier = Modifier.padding(end = 3.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_selected_arrow_right),
                                contentDescription = null,
                                tint = MaterialTheme.colors.primary,
                                modifier = Modifier.size(10.dp)
                            )
                        }
                    }
                    Column {
                        Text(
                            text = "Remove from Watched list.",
                            fontWeight = FontWeight.Light
                        )
                    }
                }

                // Change watch date
                var watchDate by remember { mutableStateOf(viewModel.clickedMovie!!.dateWatched) }
                AnimatedVisibility(visible = editDateVisibility) {
                    var updateDateBt by remember { mutableStateOf(false) }
                    Column {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            EditDateComponent(
                                date = viewModel.clickedMovie!!.dateWatched,
                                onChange = {
                                    watchDate = it
                                    updateDateBt = true
                                }
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp)
                        ) {
                            Column {
                                Button(onClick = {
                                    viewModel.onEvent(
                                        WatchedMoviesEvent.OnSaveClick(
                                            newRating = viewModel.clickedMovie!!.userRating,
                                            newWatchDate = watchDate
                                        )
                                    )
                                    viewModel.showEditDialog.value = false
                                }, enabled = updateDateBt) {
                                    Text(text = "Update date")
                                }
                            }
                            Column {
                                Button(onClick = { viewModel.showEditDialog.value = false }) {
                                    Text(text = "Cancel")
                                }
                            }

                        }
                    }
                }

                // Change movie rating
                var rating by remember { mutableStateOf(viewModel.clickedMovie!!.userRating) }
                AnimatedVisibility(visible = editRatingVisibility) {
                    var updateRatingBT by remember { mutableStateOf(false) }
                    Column {
                        Row {
                            RatingPicker(
                                selectedRating = rating,
                                onRatingClick = {
                                    rating = it
                                    updateRatingBT = true
                                }
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp)
                        ) {
                            Column {
                                Button(onClick = {
                                    viewModel.onEvent(
                                        WatchedMoviesEvent.OnSaveClick(
                                            newRating = rating,
                                            newWatchDate = viewModel.clickedMovie!!.dateWatched
                                        )
                                    )
                                    viewModel.showEditDialog.value = false
                                }, enabled = updateRatingBT) {
                                    Text(text = "Update rating")
                                }
                            }
                            Column {
                                Button(
                                    onClick = { viewModel.showEditDialog.value = false }
                                ) {
                                    Text(text = "Cancel")
                                }
                            }

                        }
                    }
                }

                // Remove movie from watched movies
                var removeButtonStatus by remember {
                    mutableStateOf(false)
                }
                AnimatedVisibility(visible = removeVisibility) {
                    Column {
                        Row {
                            Text(text = "Are you sure you want to remove this movie from your Watched list?")
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Checkbox(
                                    checked = removeButtonStatus,
                                    onCheckedChange = { removeButtonStatus = !removeButtonStatus }
                                )
                            }
                            Column {
                                Text(text = "Confirm.")
                            }

                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp)
                        ) {
                            Column {
                                Button(onClick = {
                                    viewModel.onEvent(
                                        WatchedMoviesEvent.OnRemoveWatchedMovieClick(viewModel.clickedMovie!!)
                                    )
                                    viewModel.showEditDialog.value = false
                                }, enabled = removeButtonStatus)
                                {
                                    Text(text = "Remove")
                                }
                            }
                            Column {
                                Button(onClick = { viewModel.showEditDialog.value = false }) {
                                    Text(text = "Cancel")
                                }
                            }

                        }
                    }
                }

            }
        }
    }

}