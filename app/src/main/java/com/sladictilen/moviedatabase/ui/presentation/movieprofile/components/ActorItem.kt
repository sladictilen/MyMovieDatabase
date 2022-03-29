package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
            .width(120.dp)
            .height(IntrinsicSize.Min)
            .padding(10.dp),
        backgroundColor = MaterialTheme.colors.background,
        onClick = { /* TODO on Actor Click */ }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (!profileImg.isNullOrBlank()) {
                    GlideImage(
                        imageModel = "https://image.tmdb.org/t/p/w185$profileImg",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                            .border(2.dp, MaterialTheme.colors.primary, CircleShape)
                    )
                } else {
                    GlideImage(
                        imageModel = R.drawable.ic_no_photo,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                            .border(2.dp, MaterialTheme.colors.primary, CircleShape)
                    )
                }


            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = name,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "as $characterName",
                    fontWeight = FontWeight.ExtraLight,
                    color = Color.Gray
                )
            }
        }


    }
}