package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.FabButtonState
import kotlinx.coroutines.launch

@Composable
fun MultiFabItem(
    onClick: () -> Unit,
    onAddToWatchListClick: () -> Unit,
    onMarkAsWatchedClick: () -> Unit,
    fabState: FabButtonState
) {
    val coroutineScope = rememberCoroutineScope()
    val offsetY = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    Column(horizontalAlignment = Alignment.End) {
        when (fabState) {
            FabButtonState.EXTENDED -> {
                Row(modifier = Modifier.offset(y = offsetY.value.toInt().dp, x = 0.dp)) {
                    SmallerFabItem(
                        onClick = { onMarkAsWatchedClick() },
                        text = "Mark as watched",
                        icon = R.drawable.ic_watched
                    )
                }
                Row() {
                    SmallerFabItem(
                        onClick = { onAddToWatchListClick() },
                        text = "Add to To-Watch list",
                        icon = R.drawable.ic_add_bookmark
                    )
                }


            }
            else -> {}
        }

        Row {
            FloatingActionButton(
                onClick = {
                    onClick()
                },
                modifier = Modifier,
                backgroundColor = MaterialTheme.colors.primary,
                elevation = FloatingActionButtonDefaults.elevation(5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_dots),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }

}

@Composable
private fun SmallerFabItem(onClick: () -> Unit, text: String, icon: Int) {
    Row(
        modifier = Modifier
            .padding(end = 6.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
                    .clickable { onClick }
            ) {
                Text(
                    text = text,
                    color = Color.Black,
                    modifier = Modifier.padding(
                        top = 5.dp,
                        bottom = 5.dp,
                        start = 10.dp,
                        end = 10.dp
                    )
                )
            }

        }
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .clickable { onClick() }) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )
            }
        }
    }
}