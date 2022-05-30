package com.sladictilen.moviedatabase.ui.presentation.movieprofile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Velocity
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.components.MovieProfileContent
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.components.MovieProfileHeader
import com.sladictilen.moviedatabase.util.UiEvent

@OptIn(ExperimentalMaterialApi::class)
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


    val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)
    val connection = remember {
        object : NestedScrollConnection {

            override fun onPreScroll( // Desides if use the sroll for parent (Swipe) or pass it to the childern
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                return if (delta < 0) {
                    swipingState.performDrag(delta).toOffset()
                } else {
                    Offset.Zero
                }
            }

            override fun onPostScroll( // If there is any leftover sroll from childern, let's try to use it on parent swipe
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                return swipingState.performDrag(delta).toOffset()
            }

            override suspend fun onPostFling( // Let's try to use fling on parent and pass all leftover to children
                consumed: Velocity,
                available: Velocity
            ): Velocity {
                swipingState.performFling(velocity = available.y)
                return super.onPostFling(consumed, available)
            }

            private fun Float.toOffset() = Offset(0f, this)
        }
    }
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        val heightInPx =
            with(LocalDensity.current) { maxHeight.toPx() } // Get height of the available space

        Box(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = swipingState,
                    thresholds = { _, _ -> FractionalThreshold(0.5f) }, // Threshold defining progress fraction in which should animation automatically snap to target anchor
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        // Maps anchor points (in px) to states
                        0f to SwipingStates.COLLAPSED,
                        heightInPx to SwipingStates.EXPANDED,
                    )
                )
                .nestedScroll(connection)
        ) {

                MovieProfileHeader(
                    progress =
                    if (swipingState.progress.to == SwipingStates.COLLAPSED) swipingState.progress.fraction
                    else 1f - swipingState.progress.fraction,
                    onBackClick = { viewModel.onEvent(MovieProfileEvent.OnBackPressed) }
                ) {
                    MovieProfileContent(viewModel = viewModel)
                }

        }
    }


}

enum class SwipingStates {
    EXPANDED,
    COLLAPSED
}

