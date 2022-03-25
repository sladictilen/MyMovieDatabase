package com.sladictilen.moviedatabase.ui.presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchItem(
    title: String,
    year: String,
    poster: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clickable {
                onClick()
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Poster
            Column(modifier = Modifier.width(30.dp)) {
                GlideImage(
                    imageModel = "https://image.tmdb.org/t/p/w92$poster",
                    contentScale = ContentScale.FillBounds,
                )
            }
            // Title & Year
            // Fixing if year not announced
            val yearF = if (year == "") {
                "TBA"
            } else {
                year.substring(0, 4)
            }
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Text(text = title, fontWeight = FontWeight.Bold)
            }
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Text(
                    text = "($yearF)",
                    color = Color.Gray,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 14.sp
                )
            }
        }
    }
}