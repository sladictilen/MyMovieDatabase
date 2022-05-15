package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.sladictilen.moviedatabase.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ActorItem(
    name: String,
    characterName: String,
    profileImg: String?
) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .height(150.dp)
            .width(250.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(5.dp)),
        backgroundColor = Color.DarkGray,
        onClick = { /* TODO on Actor Click */ }) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Start) {
            Column(modifier = Modifier.weight(1f)) {
                if (!profileImg.isNullOrBlank()) {
                    GlideImage(
                        imageModel = "https://image.tmdb.org/t/p/w185$profileImg",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                    )
                } else {
                    GlideImage(
                        imageModel = R.drawable.ic_no_photo,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                    )
                }
            }
            Column(modifier = Modifier
                .padding(start = 5.dp, top = 5.dp)
                .weight(2f)) {
                Row() {
                    Text(name, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colors.primary)
                }
                Row() {
                    Text("as $characterName", fontWeight = FontWeight.Light, color = Color.LightGray)
                }
            }
        }
    }
}