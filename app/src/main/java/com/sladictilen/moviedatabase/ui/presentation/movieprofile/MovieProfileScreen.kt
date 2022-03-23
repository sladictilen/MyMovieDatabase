package com.sladictilen.moviedatabase.ui.presentation.movieprofile

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MovieProfileScreen(
    viewModel: MovieProfileViewModel = hiltViewModel()
) {
    Row() {
        Text(text = viewModel.title)
    }
}