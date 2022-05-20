package com.sladictilen.moviedatabase.ui.presentation.watchlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.data.database.ToWatchData
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieItem(
    movie: ToWatchData,
    onRemove: () -> Unit,
    onWatched: () -> Unit
) {
    // Used in ToWatchListScreen and WatchedListScreen
    val buttonState = remember {
        mutableStateOf(false)
    }
    val swipeableState = rememberSwipeableState(initialValue = 0)
    when (swipeableState.currentValue) {
        1 -> {
            buttonState.value = true
        }
        else -> {
            buttonState.value = false
        }
    }
    val squareSize = 200.dp

    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, -sizePx to 1)
    Box(
        modifier = Modifier
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                orientation = Orientation.Horizontal,
            )
            .clip(RoundedCornerShape(5.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            // Poster
            Box(
                modifier = Modifier
                    .height(125.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
            ) {
                // Back layer where the buttons go ;)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Column(
                        modifier = Modifier
                            .width(100.dp)
                            .fillMaxHeight()
                            .background(Color.Red.copy(alpha = 0.3f)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            IconButton(onClick = { onRemove() }, enabled = buttonState.value) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_trash),
                                    contentDescription = "remove from list",
                                    Modifier.size(25.dp)
                                )
                            }
                        }
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Text(text = "Remove", fontSize = 15.sp)
                        }
                    }
                    Column(
                        modifier = Modifier
                            .width(100.dp)
                            .fillMaxHeight()
                            .background(Color.Green.copy(alpha = 0.3f)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            IconButton(onClick = { onWatched() }, enabled = buttonState.value) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_green_checkmark),
                                    contentDescription = "add to watched",
                                    Modifier.size(30.dp)
                                )
                            }
                        }
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = "Watched",
                                fontSize = 13.sp,
                            )
                        }
                    }
                }

                // Top Layer
                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                swipeableState.offset.value.roundToInt(), 0
                            )
                        }
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .height(150.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterStart)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(1.dp))
                            .background(MaterialTheme.colors.background)
                    ) {
                        // Poster Image
                        Column(
                            modifier = Modifier
                                .width(90.dp)
                                .fillMaxHeight()
                        ) {
                            GlideImage(
                                imageModel = "https://image.tmdb.org/t/p/w185${movie.posterUrl}",
                                contentScale = ContentScale.FillBounds,
                            )
                        }
                        // Info Column
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .width(220.dp)
                        ) {
                            // Year and runtime
                            Row() {
                                Text(
                                    text = "${movie.year} | ${movie.runtime} min",
                                    fontWeight = FontWeight.ExtraLight,
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                            // Title
                            Row() {
                                Text(movie.title, fontWeight = FontWeight.Bold)
                            }
                            // Genre
                            Row() {
                                Text(
                                    text = movie.genre,
                                    fontWeight = FontWeight.ExtraLight,
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                        }
                        // Ratings
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 5.dp)
                            ) {
                                Column(modifier = Modifier.padding(end = 5.dp)) {
                                    Text("${movie.imdbRating}")
                                }
                                Column() {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_imdb),
                                        contentDescription = null,
                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.padding(end = 5.dp)) {
                                    Text(text = "${movie.tomatoRating}%")
                                }
                                Column() {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_rotten_tomatoes),
                                        contentDescription = null,
                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                            }


                        }
                    }

                }
            }

        }
    }
}
