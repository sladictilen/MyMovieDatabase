package com.sladictilen.moviedatabase.util.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sladictilen.moviedatabase.R

@Composable
fun CustomLinkButton(
    icon: Int,
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(backgroundColor)
            .height(50.dp)
            .width(IntrinsicSize.Max)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 5.dp), verticalArrangement = Arrangement.Center
            ) {
                Icon(painter = painterResource(id = icon), contentDescription = null)
            }
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = text)
            }
        }
    }
}

@Preview
@Composable
fun preview() {
    CustomLinkButton(
        icon = R.drawable.ic_movie_media_player,
        text = "test",
        backgroundColor = Color.Black,
        onClick = {}
    )
}