package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.theme.backgroundColor

@Composable
fun WatchTrailerButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .wrapContentSize()
            .clickable {
                onClick()
            }
            .background(Color.Red)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.ic_youtube),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
            Column(modifier = Modifier.padding(start = 5.dp)) {
                Text(text = "Watch trailer", fontSize = 16.sp)
            }
        }
    }
}