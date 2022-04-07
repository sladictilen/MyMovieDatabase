package com.sladictilen.moviedatabase.ui.presentation.discover.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.discover.DiscoverEvent

@Composable
fun MoviePosterItem(
    posterUrl: String,
    isWatched: Boolean,
    isOnWatchList: Boolean,
    onClick: () -> Unit,
    onAddToWatchListClick: () -> Unit,
    onRemoveFromToWatchListClick: () -> Unit
) {
    val watchlist = remember { mutableStateOf(isOnWatchList) }
    Box(
        modifier = Modifier
            .height(240.dp)
            .clip(RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.TopEnd
    ) {

        GlideImage(
            imageModel = "https://image.tmdb.org/t/p/w500${posterUrl}",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.clickable {
                onClick()
            }
        )


        if (isWatched) {
            Row(modifier = Modifier.padding(top = 10.dp, end = 5.dp)) {
                WatchedText()
            }
        } else {
            when (watchlist.value) {
                false -> {
                    IconButton(onClick = {
                        watchlist.value = true
                        onAddToWatchListClick()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_bookmark),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
                true -> {
                    IconButton(onClick = {
                        watchlist.value = false
                        onRemoveFromToWatchListClick()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_remove_bookmark),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }


    }


}

@Composable
private fun WatchedText() {
    /*TODO */
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colors.primary),
    ) {
        Text(
            text = "WATCHED",
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraLight
        )
    }

}