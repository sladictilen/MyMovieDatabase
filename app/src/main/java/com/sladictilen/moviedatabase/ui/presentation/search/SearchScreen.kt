package com.sladictilen.moviedatabase.ui.presentation.search

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

        // Bottom padding so it wont go under bottom navbar
        Row(
            modifier = Modifier.padding(top = 10.dp, bottom = 40.dp)
        ) {
            LazyColumn() {
                items(viewModel.searchResults) {
                    if (it.poster_path != null) {
                        Row(modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth()) {
                            SearchItem(
                                title = it.title,
                                year = it.release_date,
                                poster = it.poster_path,
                                onClick = {}
                            )
                        }

                    }
                }
            }
        }
    }
}


