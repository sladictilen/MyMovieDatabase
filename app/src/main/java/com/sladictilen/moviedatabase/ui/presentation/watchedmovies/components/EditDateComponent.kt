package com.sladictilen.moviedatabase.ui.presentation.watchedmovies.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun EditDateComponent(date: String, onChange: (String) -> Unit) {
    val dateSplitted = getDateSeparated(date)   // [day, month, year]

    var currentDayNumber by remember { mutableStateOf(dateSplitted[0].toInt()) }
    var currentMonthNumber by remember { mutableStateOf(dateSplitted[1].toInt()) }
    var currentYearNumber by remember { mutableStateOf(dateSplitted[2].toInt()) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column() {

                CustomNumberPicker(
                    startNumber = 1,
                    endNumber = 31,
                    currentNumber = currentDayNumber,
                    onNumberChange = {
                        currentDayNumber = it
                        onChange("$currentDayNumber.$currentMonthNumber.$currentYearNumber")
                    }
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 2.dp, end = 2.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "/", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Column() {

                CustomNumberPicker(
                    startNumber = 1,
                    endNumber = 12,
                    currentNumber = dateSplitted[1].toInt(),
                    onNumberChange = {
                        currentMonthNumber = it
                        onChange("$currentDayNumber.$currentMonthNumber.$currentYearNumber")
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 2.dp, end = 2.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "/", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Column(modifier = Modifier.padding(start = 10.dp)) {

                CustomNumberPicker(
                    startNumber = 1,
                    currentNumber = currentYearNumber,
                    onNumberChange = {
                        currentYearNumber = it
                        onChange("$currentDayNumber.$currentMonthNumber.$currentYearNumber")
                    }
                )
            }

        }
    }
}

fun getDateSeparated(date: String): Array<String> {
    return date.split(".").toTypedArray()
}
