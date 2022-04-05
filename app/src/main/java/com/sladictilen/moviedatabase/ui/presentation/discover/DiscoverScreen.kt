package com.sladictilen.moviedatabase.ui.presentation.discover

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DiscoverScreen() {
    // Includes What To Watch and Trending movies
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Box(){
            Row() {
                Text(text = "Trending movies", fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
            }
        }
        Divider()


    }

}