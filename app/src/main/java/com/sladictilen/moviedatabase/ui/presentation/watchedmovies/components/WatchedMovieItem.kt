package com.sladictilen.moviedatabase.ui.presentation.watchedmovies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.util.helpers.getUserRatingImage

@Composable
fun WatchedMovieItem(
    posterUrl: String,
    title: String,
    genre: String,
    watchedDate: String,
    userRating: String
) {
    // TODO Movie rating scale - Very bad, Bad, Okay, Great, Excellent
    Box(modifier = Modifier.background(Color.DarkGray)) {


        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
        ) {
            // Poster
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(70.dp)) {
                GlideImage(
                    imageModel = "https://image.tmdb.org/t/p/w185${posterUrl}",
                    contentScale = ContentScale.FillHeight,
                )
            }
            // movie info
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(5.dp)) {
                Row() {
                    Text(text = title)
                }
                Row() {
                    Text(text = genre)
                }
                Row() {
                    Text(text = "Watched date: $watchedDate")
                }
            }
            // user rating
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(IntrinsicSize.Min),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                GlideImage(imageModel = getUserRatingImage(userRating), Modifier.size(60.dp))
            }
        }
    }

}

@Preview
@Composable
fun Prev22() {
    WatchedMovieItem(
        "/k9tJGdMkzOe17YH2ZCQjNnX5YLz.jpg",
        "Deadpool",
        "Ryan Reynolds, in ostali  test",
        "22.3.2022",
        "2"
    )
}