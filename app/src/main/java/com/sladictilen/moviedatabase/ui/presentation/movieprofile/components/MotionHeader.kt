package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionHeader(
    title: String,
    genre: String,
    length: String,
    imageUrl: String,
    progress: Float,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_header).readBytes().decodeToString()
    }


    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = modifier,
        content = {
            val imageBlurProperties = motionProperties(id = "image")

            GlideImage(
                imageModel = imageUrl,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .layoutId("image")
                    .fillMaxWidth()
                    .blur(imageBlurProperties.value.distance("blur"))
            )

            Divider(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colors.background
                            ),
                        )
                    )
                    .graphicsLayer { alpha = 0.1f }
                    .height(160.dp)
                    .layoutId("black_bar")
            )

            IconButton(onClick = { onBackClick() }, modifier = Modifier.layoutId("back")) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
            }

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .layoutId("title")
                    .width(230.dp)
            )
            Text(
                text = "$length | $genre",
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .layoutId("short_info")
            )

        }
    )
}