package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SimilarMovieItem(posterUrl: String, title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .clickable { onClick() }
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                GlideImage(
                    imageModel = "https://image.tmdb.org/t/p/w500$posterUrl",
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }

}
