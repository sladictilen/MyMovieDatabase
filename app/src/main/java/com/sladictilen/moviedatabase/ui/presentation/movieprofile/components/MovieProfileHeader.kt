package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

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
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MovieProfileHeader(
    progress: Float,
    imageUrl: String,
    title: String,
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
        modifier = Modifier.fillMaxWidth()
    ) {
        //val properties = motionProperties("movie_header")
        GlideImage(
            imageModel = "https://image.tmdb.org/t/p/w780${imageUrl}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.layoutId("image"),
            loading = {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
            }
        )
        Text(text = title, modifier = Modifier.layoutId("title"))

        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("back")) {
            Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
        }

        Box(modifier = Modifier.layoutId("content")) {
            scrollableBody()
        }
    }
}