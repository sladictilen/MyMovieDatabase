package com.sladictilen.moviedatabase.ui.presentation.watchlist.components

import android.media.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.util.helpers.getUserRatingImage

@Composable
fun RatingPicker(selectedRating: String) {
    // TODO READ FROM VIEWMODEL
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column() {
                IconButton(onClick = { /*TODO*/ }) {
                    GlideImage(
                        imageModel = getUserRatingImage("1"),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Column() {
                IconButton(onClick = { /*TODO*/ }) {
                    GlideImage(
                        imageModel = getUserRatingImage("2"),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Column() {
                IconButton(onClick = { /*TODO*/ }) {
                    GlideImage(
                        imageModel = getUserRatingImage("3"),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Column() {
                IconButton(onClick = { /*TODO*/ }) {
                    GlideImage(
                        imageModel = getUserRatingImage("4"),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Column(modifier = Modifier.width(IntrinsicSize.Min)) {
                IconButton(onClick = { /*TODO*/ }) {
                    GlideImage(
                        imageModel = getUserRatingImage("5"),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}