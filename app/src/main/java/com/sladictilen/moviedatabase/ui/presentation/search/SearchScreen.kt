package com.sladictilen.moviedatabase.ui.presentation.search

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.search.components.SearchItem
import com.sladictilen.moviedatabase.util.UiEvent

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

        // Search
        when (viewModel.searchingState) {
            SearchState.ResultFound -> {
                ResultsFoundComp(viewModel = viewModel)
            }
            SearchState.EmptySearch -> {
                EmptySearchComp()
            }
            SearchState.Searching -> {
                SearchingComp()
            }
            SearchState.NoResult -> {
                NoResultComp()
            }
        }
    }
}

@Composable
fun EmptySearchComp() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = "What are\nyou searching for?",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
            Row() {
                Text(
                    text = "Search for a movie or TV show!",
                    fontWeight = FontWeight.ExtraLight,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }

    }
}

@Composable
fun SearchingComp() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                CircularProgressIndicator()
            }
        }

    }
}

@Composable
fun NoResultComp() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = "No results found.",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }

    }
}

@Composable
fun ResultsFoundComp(viewModel: SearchViewModel) {
    Row {
        LazyColumn() {
            items(viewModel.searchResults) {
                if (it.poster_path != null) {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Log.d("Info", "${it.title}year: ${it.release_date}")
                        SearchItem(
                            title = it.title,
                            year = it.release_date,
                            poster = it.poster_path,
                            onClick = {
                                viewModel.onEvent(SearchEvent.OnSearchedItemClick(it.id))
                            }
                        )
                    }

                }
            }
        }
    }
}



