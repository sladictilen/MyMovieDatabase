package com.sladictilen.moviedatabase.ui.presentation.watchlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieItem(
    movie: MovieItemModel
) {
    val swipeableState = rememberSwipeableState(initialValue = 0)
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
            .border(1.dp, Color.White)
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
                            IconButton(onClick = { /*TODO*/ }) {
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
                            IconButton(onClick = { /*TODO*/ }) {
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
                    // Top Layer
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
                                imageModel = movie.poster,
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
                                    text = "${movie.year} | ${movie.runtime}",
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
                                    /* imdb
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_imdb),
                                        contentDescription = "Imdb logo",
                                        modifier = Modifier.size(30.dp)
                                    ) */
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.padding(end = 5.dp)) {

                                }
                                Column() {

                                }
                            }


                        }
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
        MovieItemModel(
            title = "Deadpool",
            year = 2016,
            genre = "Action, Adventure, Comedy",
            runtime = 108,
            poster = "https://m.media-amazon.com/images/M/MV5BYzE5MjY1ZDgtMTkyNC00MTMyLThhMjAtZGI5OTE1NzFlZGJjXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
            imdbRating = 8.0,
            tomatoRating = 85
        )
    )
}