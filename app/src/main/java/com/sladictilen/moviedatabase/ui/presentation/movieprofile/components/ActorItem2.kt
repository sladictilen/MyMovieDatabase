package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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

@Composable
fun ActorItem2(
    name: String,
    characterName: String,
    profileImg: String?
) {
    Card() {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier) {
                if (!profileImg.isNullOrBlank()) {
                    GlideImage(
                        imageModel = "https://image.tmdb.org/t/p/w185$profileImg",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .border(2.dp, MaterialTheme.colors.primary)
                    )
                } else {
                    GlideImage(
                        imageModel = R.drawable.ic_no_photo,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .border(2.dp, MaterialTheme.colors.primary)
                    )
                }
            }
            Column(modifier = Modifier) {
                Row() {
                    Text(name, fontWeight = FontWeight.ExtraBold)
                }
                Row() {
                    Text("as $characterName", fontWeight = FontWeight.Light, color = Color.DarkGray)
                }
            }
        }
    }
}