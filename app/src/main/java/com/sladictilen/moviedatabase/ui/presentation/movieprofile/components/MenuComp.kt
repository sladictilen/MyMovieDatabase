package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.MovieProfileViewModel

@Composable
fun MenuComp(
    addToWatchListClick: () -> Unit,
    markAsWatchedClick: () -> Unit,
    viewModel: MovieProfileViewModel
) {
    Box(modifier = Modifier.clip(RoundedCornerShape(4.dp))) {
        Column() {
            Row() {
                Text(text = "Remove from watched")
            }
        }
    }
}