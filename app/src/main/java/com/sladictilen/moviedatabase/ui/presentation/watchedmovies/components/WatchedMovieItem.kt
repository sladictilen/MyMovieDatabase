package com.sladictilen.moviedatabase.ui.presentation.watchedmovies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    actors: String,
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
            // Img
            Column(modifier = Modifier.fillMaxHeight().border(1.dp, Color.Green).width(100.dp)) {
                GlideImage(
                    imageModel = "https://image.tmdb.org/t/p/w185${posterUrl}",
                    contentScale = ContentScale.FillHeight,
                )
            }
            // movie info
            Column(modifier = Modifier.fillMaxHeight().border(1.dp, Color.Red)) {
                Row() {
                    Text(text = title)
                }
                Row() {
                    Text(text = actors)
                }
                Row() {
                    Text(text = watchedDate)
                }
            }
            // user rating
            Column(modifier = Modifier.fillMaxHeight().border(1.dp, Color.Blue)) {
                GlideImage(imageModel = getUserRatingImage(userRating), Modifier.size(40.dp))
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
        "Ryan Reynolds, in ostali",
        "22.3.2022",
        "2"
    )
}