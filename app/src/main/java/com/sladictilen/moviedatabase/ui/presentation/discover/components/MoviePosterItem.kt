package com.sladictilen.moviedatabase.ui.presentation.discover.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R

@Composable
fun MoviePosterItem(posterUrl: String, movie_id: Int) {
    Box(modifier = Modifier.height(240.dp), contentAlignment = Alignment.TopEnd) {

        GlideImage(
            imageModel = "https://image.tmdb.org/t/p/w500${posterUrl}",
            contentScale = ContentScale.FillHeight
        )
        WatchedText()
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_bookmark),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colors.primary
            )
        }


    }

}

@Composable
private fun WatchedText() {
    /*TODO */
    Box(
        modifier = Modifier
            .padding(5.dp)
            .background(MaterialTheme.colors.primary)
            .clip(RoundedCornerShape(30.dp))
    ) {
        Text(text = "WATCHED")
    }

}