package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.MovieProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MovieProfileHeader(
    progress: Float,
    viewModel: MovieProfileViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    scrollableBody: @Composable () -> Unit
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxSize()
    ) {
        when (viewModel.allLoaded) {
            true -> {
                Box(modifier = Modifier.layoutId("content")) {
                    scrollableBody()
                }
                GlideImage(
                    imageModel = viewModel.backdropImageUrl,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .layoutId("image")
                        .heightIn(min = 230.dp),
                    loading = {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colors.primary)
                        }
                    },

                    )
                Text(text = viewModel.title, modifier = Modifier.layoutId("title"))


                IconButton(onClick = { onBackClick() }, modifier = Modifier.layoutId("back")) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null
                    )
                }


            }
            else -> {}
        }

    }
}