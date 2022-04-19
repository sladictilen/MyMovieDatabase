package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.MovieProfileEvent
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.MovieProfileViewModel

@Composable
fun MovieProfileContent(viewModel: MovieProfileViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            GlideImage(
                imageModel = "https://image.tmdb.org/t/p/w780${viewModel.posterUrl}",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                loading = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colors.primary)
                    }
                }
            )
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
                Text(
                    text = viewModel.releaseDate,
                    fontWeight = FontWeight.ExtraLight
                )
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