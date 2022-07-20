package com.sladictilen.moviedatabase.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sladictilen.moviedatabase.R

@Composable
fun CustomNumberPicker(
    startNumber: Int,
    endNumber: Int? = null,
    currentNumber: Int?,
    onNumberChange: (Int) -> Unit
) {
    var number by remember { mutableStateOf(currentNumber ?: startNumber) }
    Box(modifier = Modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row() {
                IconButton(onClick = {
                    if (endNumber == null || number < endNumber) number += 1
                    onNumberChange(number)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_up),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
            Row() {
                Text(text = "$number", fontSize = 20.sp)
            }
            Row() {
                IconButton(onClick = {
                    if (number > startNumber) number -= 1
                    onNumberChange(number)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }

    }
}