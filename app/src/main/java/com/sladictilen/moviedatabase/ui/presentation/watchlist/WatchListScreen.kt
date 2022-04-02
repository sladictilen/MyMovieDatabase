package com.sladictilen.moviedatabase.ui.presentation.watchlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.watchlist.components.MovieItem

@Composable
fun WatchListScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "WATCH LIST", fontSize = 20.sp)
            }
            Column(verticalArrangement = Arrangement.Center) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sort),
                        contentDescription = "Sort button",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }
        Row() {
        }

    }


}