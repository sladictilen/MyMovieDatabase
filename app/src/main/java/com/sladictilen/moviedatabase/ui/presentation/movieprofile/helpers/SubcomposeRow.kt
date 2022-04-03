package com.sladictilen.moviedatabase.ui.presentation.movieprofile.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import java.lang.reflect.Modifier

@Composable
fun SubcomposeRow(
    modifier: androidx.compose.ui.Modifier,
    content: @Composable () -> Unit = {}
) {
    // From https://stackoverflow.com/questions/71080209/jetpack-compose-row-with-all-items-same-height Compose bug fix
    /*TODO try to update SimilarMovieItem with modifier.fillmaxheight() */

    SubcomposeLayout(modifier = modifier) { constraints ->

        var recompositionIndex = 0

        var placeables: List<Placeable> = subcompose(recompositionIndex++, content).map {
            it.measure(constraints)
        }

        placeables.forEachIndexed() { index: Int, placeable: Placeable ->
            println("Index: $index, placeable width: ${placeable.width}, height: ${placeable.height}")
        }

        var rowSize =
            placeables.fold(IntSize.Zero) { currentMax: IntSize, placeable: Placeable ->
                IntSize(
                    width = currentMax.width + placeable.width,
                    height = maxOf(currentMax.height, placeable.height)
                )
            }


        // Remeasure every element using height of longest item as minHeight of Constraint
        if (!placeables.isNullOrEmpty() && placeables.size > 1) {
            placeables = subcompose(recompositionIndex, content).map { measurable: Measurable ->
                measurable.measure(
                    Constraints(
                        minHeight = rowSize.height,
                        maxHeight = constraints.maxHeight
                    )
                )
            }

            rowSize =
                placeables.fold(IntSize.Zero) { currentMax: IntSize, placeable: Placeable ->
                    IntSize(
                        width = currentMax.width + placeable.width,
                        height = maxOf(currentMax.height, placeable.height)
                    )
                }
        }

        layout(rowSize.width, rowSize.height) {
            var xPos = 0
            placeables.forEach { placeable: Placeable ->
                placeable.placeRelative(xPos, 0)
                xPos += placeable.width
            }

        }
    }
}