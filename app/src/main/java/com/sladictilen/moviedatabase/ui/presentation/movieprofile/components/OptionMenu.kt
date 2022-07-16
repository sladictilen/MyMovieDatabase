package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OptionMenu() {
    Box(
        modifier = Modifier
            .width(120.dp)
            .background(Color.Gray)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row() {
                Text(text = "Add to your To-Watch list")
            }
            Row() {
                Text(text = "Mark as watched")
            }
            Row() {
                Text(text = "Share")
            }
        }
    }
}