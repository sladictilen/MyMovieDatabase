package com.sladictilen.moviedatabase.ui.presentation.discover.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieItem(
    title: String,
    year: String,
    genre: String,
    runtime: String,
    poster: String,
    imdbRating: String,
    rottentomatoRating: String
) {
    Row(modifier = Modifier.padding(10.dp)) {
        // Poster
        Box(
            modifier = Modifier
                .height(125.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(3.dp))
            ) {
                // Poster Image
                Column(
                    modifier = Modifier.width(90.dp)
                ) {
                    GlideImage(
                        imageModel = poster,
                        contentScale = ContentScale.FillBounds,
                    )
                }
                // Info Column
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    // Year and runtime
                    Row() {
                        Text(
                            text = "$year | $runtime",
                            fontWeight = FontWeight.ExtraLight,
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                    // Title
                    Row() {
                        Text(title, fontWeight = FontWeight.Bold)
                    }
                    // Genre
                    Row() {
                        Text(
                            text = genre,
                            fontWeight = FontWeight.ExtraLight,
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }

                }
            }
        }

    }
}


@Preview
@Composable
fun Prev() {
    MovieItem(
        title = "Deadpool",
        year = "2016",
        genre = "Action, Adventure, Comedy",
        runtime = "108 min",
        poster = "https://m.media-amazon.com/images/M/MV5BYzE5MjY1ZDgtMTkyNC00MTMyLThhMjAtZGI5OTE1NzFlZGJjXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
        imdbRating = "8.0",
        rottentomatoRating = "85%"
    )
}