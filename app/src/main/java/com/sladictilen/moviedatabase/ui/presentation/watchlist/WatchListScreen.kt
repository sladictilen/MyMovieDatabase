package com.sladictilen.moviedatabase.ui.presentation.watchlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.watchlist.components.MovieItem

@Composable
fun WatchListScreen(
    viewModel: WatchListViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "Your To-Watch List", fontSize = 20.sp)
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
        val watchList = viewModel.watchList.collectAsState(initial = emptyList())

        Row {
            LazyColumn {
                items(watchList.value) {
                    MovieItem(
                        movie = it,
                        onRemove = { viewModel.onEvent(WatchListEvent.OnRemoveMovieClick(it.id_movie)) },
                        onWatched = { viewModel.onEvent(WatchListEvent.OnMarkAsWatchedClicked(it.id_movie)) }
                    )
                }
            }
        }
    }

}


