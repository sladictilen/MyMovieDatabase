package com.sladictilen.moviedatabase.ui.presentation.movieprofile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.components.MotionHeader
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.components.MovieProfileContent
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.components.MultiFabItem
import com.sladictilen.moviedatabase.util.UiEvent
import kotlin.math.abs

@Composable
fun MovieProfileScreen(
    viewModel: MovieProfileViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
            }

        }
    }
    val toolbarHeight = 230.dp - 56.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    val connection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            val delta = available.y
            val newOffset = toolbarOffsetHeightPx.value + delta
            toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
            return Offset.Zero
        }
    }
    when (viewModel.allLoaded) {
        true -> {
            Scaffold(
                floatingActionButton = {
                    MultiFabItem(
                        onClick = {
                            if (viewModel.fabState.value == FabButtonState.EXTENDED) {
                                viewModel.fabState.value = FabButtonState.COLLAPSED
                            } else {
                                viewModel.fabState.value = FabButtonState.EXTENDED
                            }
                        },
                        onAddToWatchListClick = {
                            viewModel.onEvent(MovieProfileEvent.OnAddToWatchListButtonClick)
                            viewModel.fabState.value = FabButtonState.COLLAPSED
                        },
                        onMarkAsWatchedClick = {
                            viewModel.onEvent(MovieProfileEvent.OnAddToWatchedListButtonClick)
                            viewModel.fabState.value = FabButtonState.COLLAPSED
                        },
                        fabState = viewModel.fabState.value
                    )
                }
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(it)
                        .nestedScroll(connection)
                ) {
                    LazyColumn(contentPadding = PaddingValues(top = toolbarHeight + 56.dp)) {
                        item {
                            MovieProfileContent(viewModel)
                        }
                    }
                    MotionHeader(
                        title = viewModel.title,
                        genre = viewModel.genre,
                        length = "${viewModel.runtime} min",
                        imageUrl = "https://image.tmdb.org/t/p/w500/${viewModel.backdropImageUrl}",
                        progress = abs(toolbarOffsetHeightPx.value) / toolbarHeightPx,
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    x = 0,
                                    y = 0
                                ) // no need for offset as motion layout changes height
                            },
                        onBackClick = { onPopBackStack() }
                    )
                }
            }


        }
        else -> {
            /* TODO add loading */
        }

    }
}





