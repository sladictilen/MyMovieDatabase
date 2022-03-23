package com.sladictilen.moviedatabase.ui.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.search.components.SearchItem
import com.sladictilen.moviedatabase.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> {}
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = viewModel.searchText,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewModel.onEvent(SearchEvent.OnSearchValueChange(it)) },
                singleLine = true,
                label = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search",
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { viewModel.onEvent(SearchEvent.OnClearSearchClick) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete_search),
                            contentDescription = "Clear search",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = MaterialTheme.colors.primary,
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = Color.DarkGray,
                    focusedLabelColor = Color.DarkGray,
                    unfocusedLabelColor = MaterialTheme.colors.primary,
                    leadingIconColor = MaterialTheme.colors.primary,
                    trailingIconColor = Color.DarkGray
                )
            )
        }
        Row(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            SearchItem(
                title = "Deadpool",
                year = "2016",
                poster = "https://m.media-amazon.com/images/M/MV5BYzE5MjY1ZDgtMTkyNC00MTMyLThhMjAtZGI5OTE1NzFlZGJjXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
                onClick = {
                    viewModel.onEvent(SearchEvent.OnSearchedItemClick(it))
                }
            )
        }
    }
}