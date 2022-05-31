package com.sladictilen.moviedatabase.ui.presentation.watchedmovies.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.ui.presentation.watchedmovies.WatchedMoviesEvent
import com.sladictilen.moviedatabase.util.helpers.getUserRatingImage

@Composable
fun WatchedMovieItem(
    posterUrl: String,
    title: String,
    genre: String,
    watchedDate: String,
    userRating: String,
    onLongPress: () -> Unit,
    onClick: () -> Unit
) {

    Box(modifier = Modifier
        .background(Color.DarkGray)
        .pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    onLongPress()
                },
                onTap = {onClick()}
            )
        }

    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
            ) {
                // Poster
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(70.dp)
                ) {
                    GlideImage(
                        imageModel = "https://image.tmdb.org/t/p/w185${posterUrl}",
                        contentScale = ContentScale.FillHeight,
                    )
                }
                // movie info
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(5.dp)
                ) {
                    Row() {
                        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                    Row() {
                        Text(text = genre, fontWeight = FontWeight.Light, color = Color.Gray)
                    }
                    Row() {
                        Text(
                            text = "Watched date: $watchedDate",
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
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

}
