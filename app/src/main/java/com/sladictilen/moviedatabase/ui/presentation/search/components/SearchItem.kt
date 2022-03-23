package com.sladictilen.moviedatabase.ui.presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchItem(
    title: String,
    year: String,
    poster: String,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clickable {
                onClick(title)
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Poster
            Column(modifier = Modifier.width(30.dp)) {
                GlideImage(
                    imageModel = poster,
                    contentScale = ContentScale.FillBounds,
                )
            }
            // Title & Year
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Text(text = title, fontWeight = FontWeight.Bold)
            }
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Text(
                    text = "($year)",
                    color = Color.Gray,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 16.sp
                )
            }
        }
    }
}