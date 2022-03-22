package com.sladictilen.moviedatabase.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = green,
    primaryVariant = green,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    secondary = Color.White,
    surface = green,
    onSurface = Color.Black,
    background = backgroundColor,
    onBackground = Color.White,

)


/* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/

@Composable
fun MovieDatabaseTheme(
    content: @Composable () -> Unit
) {
    val colors = DarkColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}