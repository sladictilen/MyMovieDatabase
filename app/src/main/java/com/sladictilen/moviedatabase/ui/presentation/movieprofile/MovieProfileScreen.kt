package com.sladictilen.moviedatabase.ui.presentation.movieprofile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieProfileScreen(
    viewModel: MovieProfileViewModel = hiltViewModel()
) {
    BackdropScaffold(appBar = {
        TopAppBar(
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
                IconButton(onClick = { /* TODO popback */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier.height(IntrinsicSize.Min),
            contentColor = MaterialTheme.colors.primary
        )
    },
        frontLayerBackgroundColor = MaterialTheme.colors.background,
        backLayerBackgroundColor = MaterialTheme.colors.background,
        frontLayerScrimColor = MaterialTheme.colors.surface.copy(alpha = 0.2f),
        //frontLayerShape = RoundedCornerShape(0.dp),
        backLayerContent = {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Available platforms: ")
            }
        },
        frontLayerContent = {
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
                        imageModel = "https://image.tmdb.org/t/p/w154${viewModel.posterUrl}",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(200.dp)
                            .width(120.dp)
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
                        Text(text = "Release date: ", fontWeight = FontWeight.Bold)
                    }
                    Column() {
                        Text(text = viewModel.releaseDate)
                    }
                }
                Row() {
                    Column() {
                        Text(text = "Release date: ", fontWeight = FontWeight.Bold)
                    }
                    Column() {
                        Text(text = viewModel.releaseDate)
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
            }
        }
    )


}