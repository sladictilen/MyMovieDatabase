package com.sladictilen.moviedatabase.ui.presentation.movieprofile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.components.ActorItem
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.components.SimilarMovieItem
import com.sladictilen.moviedatabase.util.UiEvent
import kotlin.math.roundToInt

@Composable
fun MovieProfileScreen(
    viewModel: MovieProfileViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
            }

        }
    }
    val fraction = 0.3f
    val scrollState = rememberScrollState()
    val imageOffset = (-scrollState.value * 0.15f)

    val height = 200.dp
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Layout(
                modifier = Modifier
                    .height(height + imageOffset.dp),
                content = {

                    GlideImage(
                        imageModel = "https://image.tmdb.org/t/p/w780${viewModel.backdropImageUrl}",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier,
                        loading = {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .graphicsLayer { translationY = imageOffset },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(color = MaterialTheme.colors.primary)
                            }
                        }
                    )
                    Text(text = viewModel.title)

                }
            ) { measurables, constraints ->
                val image = measurables[0].measure(constraints)
                val title = measurables[1].measure(constraints)

                /* TODO make it like TopAppBarDefaults.exitUntilCollapsedScrollBehavior */

                layout(constraints.maxWidth, constraints.maxHeight) {
                    image.place(0, 0)
                    title.place(
                        (constraints.maxWidth - title.width) / 2,
                        image.height - 80
                    )
                    Log.d("Info", title.height.toString())
                }
            }
            /*TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Text(
                            text = viewModel.title,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(MovieProfileEvent.OnBackPressed) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.height(IntrinsicSize.Min),
                contentColor = MaterialTheme.colors.primary,
            )*/

        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .verticalScroll(scrollState)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    /*GlideImage(
                        imageModel = "https://image.tmdb.org/t/p/w780${viewModel.backdropImageUrl}",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth(),
                        loading = {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(color = MaterialTheme.colors.primary)
                            }
                        }
                    )*/
                }
                // Ratings
                Row(
                    Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(modifier = Modifier.padding(end = 5.dp)) {
                        Row(modifier = Modifier.size(30.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_imdb),
                                contentDescription = null
                            )
                        }
                        Row(horizontalArrangement = Arrangement.Center) {
                            Text(text = viewModel.imdbRating)
                        }
                    }
                    Column() {
                        Row(modifier = Modifier.size(30.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_rotten_tomatoes),
                                contentDescription = null
                            )
                        }
                        Row(horizontalArrangement = Arrangement.Center) {
                            Text(text = viewModel.rottenTomatoesRating)
                        }
                    }
                }
                // Watched?
                Row() {
                    Column() {
                        Text(text = "Status: ")
                    }
                    Column() {
                        Text(text = viewModel.watched, color = viewModel.watchedColor)
                    }
                    Column(modifier = Modifier.padding(10.dp)) {
                        IconButton(onClick = { viewModel.onEvent(MovieProfileEvent.OnAddToWatchListButtonClick) }) {
                            when (viewModel.isOnWatchList) {
                                true -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_remove_bookmark),
                                        contentDescription = "Remove from to-watch list",
                                        tint = MaterialTheme.colors.primary,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                                false -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_add_bookmark),
                                        contentDescription = "Add to to-watch list",
                                        tint = MaterialTheme.colors.primary,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                // Tagline
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = viewModel.tagline,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 16.sp,
                    )
                }
                Row(
                    modifier = Modifier.padding(
                        top = 10.dp,
                    )
                ) {
                    Text(
                        text = "Details",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }
                Row() {
                    Column() {
                        Text(text = "Genre: ", fontWeight = FontWeight.SemiBold)
                    }
                    Column() {
                        Text(text = viewModel.genre, fontWeight = FontWeight.ExtraLight)
                    }
                }
                Row() {
                    Column() {
                        Text(text = "Release date: ", fontWeight = FontWeight.SemiBold)
                    }
                    Column() {
                        Text(text = viewModel.releaseDate, fontWeight = FontWeight.ExtraLight)
                    }
                }
                Row() {
                    Column() {
                        Text(text = "Runtime: ", fontWeight = FontWeight.SemiBold)
                    }
                    Column() {
                        Text(
                            text = "${viewModel.runtime} minutes",
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(
                        top = 10.dp,
                    )
                ) {
                    Text(
                        text = "Overview",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }
                Row() {
                    Text(text = viewModel.overview)
                }
                Row(
                    modifier = Modifier.padding(
                        top = 10.dp,
                    )
                ) {
                    Text(
                        text = "Cast",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }
                Row() {
                    LazyRow() {
                        items(viewModel.cast) {
                            ActorItem(
                                name = it.name,
                                characterName = it.character,
                                profileImg = it.profile_path
                            )
                        }

                    }
                }
                Row(
                    modifier = Modifier.padding(
                        top = 10.dp,
                        bottom = 5.dp
                    )
                ) {
                    Text(
                        text = "Similar",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }
                Row() {
                    LazyRow() {

                        items(viewModel.similarMovies) {
                            Column(
                                modifier = Modifier
                                    .padding(end = 10.dp)
                            ) {

                                SimilarMovieItem(
                                    posterUrl = it.poster_path,
                                    title = it.title,
                                    onClick = {
                                        viewModel.onEvent(
                                            MovieProfileEvent.OnSimilarMovieClick(
                                                it.id
                                            )
                                        )
                                    }
                                )

                            }

                        }


                    }
                }

            }
        }
    )


}