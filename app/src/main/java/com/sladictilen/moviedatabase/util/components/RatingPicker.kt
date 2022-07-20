package com.sladictilen.moviedatabase.util.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.util.helpers.getUserRatingImage

@Composable
fun RatingPicker(selectedRating: String, onRatingClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { onRatingClick("1") }) {
                    GlideImage(
                        imageModel = getUserRatingImage("1"),
                        modifier = Modifier.size(40.dp)
                    )
                }
                if (selectedRating == "1") {
                    Divider(
                        color = MaterialTheme.colors.primary,
                        thickness = 3.dp,
                        modifier = Modifier
                            .width(30.dp)
                            .clip(
                                RoundedCornerShape(20.dp)
                            )
                    )
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { onRatingClick("2") }) {
                    GlideImage(
                        imageModel = getUserRatingImage("2"),
                        modifier = Modifier.size(40.dp)
                    )
                }
                if (selectedRating == "2") {
                    Divider(
                        color = MaterialTheme.colors.primary,
                        thickness = 3.dp,
                        modifier = Modifier
                            .width(30.dp)
                            .clip(
                                RoundedCornerShape(20.dp)
                            )
                    )
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { onRatingClick("3") }) {
                    GlideImage(
                        imageModel = getUserRatingImage("3"),
                        modifier = Modifier.size(40.dp)
                    )
                }
                if (selectedRating == "3") {
                    Divider(
                        color = MaterialTheme.colors.primary,
                        thickness = 3.dp,
                        modifier = Modifier
                            .width(30.dp)
                            .clip(
                                RoundedCornerShape(20.dp)
                            )
                    )
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { onRatingClick("4") }) {
                    GlideImage(
                        imageModel = getUserRatingImage("4"),
                        modifier = Modifier.size(40.dp)
                    )
                }
                if (selectedRating == "4") {
                    Divider(
                        color = MaterialTheme.colors.primary,
                        thickness = 3.dp,
                        modifier = Modifier
                            .width(30.dp)
                            .clip(
                                RoundedCornerShape(20.dp)
                            )
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                IconButton(onClick = { onRatingClick("5") }) {
                    GlideImage(
                        imageModel = getUserRatingImage("5"),
                        modifier = Modifier.size(40.dp)
                    )
                }
                if (selectedRating == "5") {
                    Divider(
                        color = MaterialTheme.colors.primary,
                        thickness = 3.dp,
                        modifier = Modifier
                            .width(30.dp)
                            .clip(
                                RoundedCornerShape(20.dp)
                            )
                    )
                }
            }

        }
    }
}