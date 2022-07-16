package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
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
fun MovieProfileContent(viewModel: MovieProfileViewModel) {
    val sectionDividerAlpha = 0.3f
    val uriHandler = LocalUriHandler.current

    Column(modifier = Modifier.padding(top = 10.dp)) {
        // Release date
        Row(
            Modifier.padding(start = 10.dp, end = 5.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Icon(
                    painter = painterResource(id = R.drawable.ic_release_date),
                    contentDescription = null,
                    modifier = Modifier.height(18.dp),
                    tint = Color.Gray
                )
            }
            Column(modifier = Modifier.padding(start = 5.dp)) {
                Text(text = viewModel.releaseDate, color = Color.Gray, fontSize = 14.sp)
            }
            // Watch status
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_watched),
                    contentDescription = null,
                    modifier = Modifier.height(12.dp),
                    tint = Color.Gray
                )
            }
            Column(modifier = Modifier.padding(start = 5.dp)) {
                Text(text = viewModel.watched, color = Color.Gray, fontSize = 14.sp)
            }
        }

        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.alpha(sectionDividerAlpha)
        )

        // Watch on
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Text(text = viewModel.tagline, fontStyle = FontStyle.Italic)
        }

        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.alpha(sectionDividerAlpha)
        )

        // Overview
        Row(modifier = Modifier.padding(10.dp)) {
            Text(text = "Overview", color = Color.Gray)
        }
        Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
            Text(text = viewModel.overview)
        }

        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.alpha(sectionDividerAlpha)
        )

        // TODO -  Trailer

        Row(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ) {
            Column(
                modifier = Modifier
                    .width(180.dp)
                    .padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    GlideImage(
                        imageModel = "https://img.youtube.com/vi/${viewModel.youtubeKey}/mqdefault.jpg",
                        contentScale = ContentScale.FillHeight
                    )
                }

            }
            Column {
                WatchTrailerButton(onClick = { uriHandler.openUri(viewModel.youtubeTrailerURL) })
            }
        }

        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.alpha(sectionDividerAlpha)
        )

        // Cast
        Row(modifier = Modifier.padding(10.dp)) {
            Text(text = "Cast", color = Color.Gray)
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            CastSection(cast = viewModel.cast)
        }

        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier
                .alpha(sectionDividerAlpha)
        )

        // Similar movies
        Row(modifier = Modifier.padding(10.dp)) {
            Text(text = "Similar", color = Color.Gray)
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
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
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier
                .alpha(sectionDividerAlpha)
        )
        // APIs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Movie data from TMDB. Ratings from OMDB.",
                fontSize = 10.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Light
            )
        }
    }
}

