package com.sladictilen.moviedatabase.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sladictilen.moviedatabase.R
import com.sladictilen.moviedatabase.ui.presentation.discover.DiscoverScreen
import com.sladictilen.moviedatabase.ui.presentation.search.SearchScreen
import com.sladictilen.moviedatabase.ui.presentation.watchedmovies.WatchedMoviesScreen
import com.sladictilen.moviedatabase.ui.presentation.watchlist.WatchListScreen
import com.sladictilen.moviedatabase.ui.theme.backgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    var selectedItem by remember { mutableStateOf(0) }

    val navItems = listOf(
        Screens.DiscoverScreen,
        Screens.SearchScreen,
        Screens.WatchListScreen,
        Screens.WatchedMovies
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = navItems[selectedItem].title)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(56.dp)
            ) {
                navItems.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(screen.route)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(24.dp)
                            )
                        })
                }

            }
        }
    ) {
        NavHost(navController = navController, startDestination = Screens.DiscoverScreen.route) {
            composable(route = Screens.DiscoverScreen.route) {
                DiscoverScreen()
            }
            composable(route = Screens.SearchScreen.route) {
                SearchScreen()
            }
            composable(route = Screens.WatchListScreen.route) {
                WatchListScreen()
            }
            composable(route = Screens.WatchedMovies.route) {
                WatchedMoviesScreen()
            }
        }
    }

}