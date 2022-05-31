package com.sladictilen.moviedatabase.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sladictilen.moviedatabase.ui.presentation.discover.DiscoverScreen
import com.sladictilen.moviedatabase.ui.presentation.movieprofile.MovieProfileScreen
import com.sladictilen.moviedatabase.ui.presentation.search.SearchScreen
import com.sladictilen.moviedatabase.ui.presentation.watchedmovies.WatchedMoviesScreen
import com.sladictilen.moviedatabase.ui.presentation.watchlist.WatchListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    var selectedItem by remember { mutableStateOf(0) }

    val bottomNavigationItems = listOf(
        BottomNavScreens.DiscoverScreen,
        BottomNavScreens.SearchScreen,
        BottomNavScreens.WatchListScreen,
        BottomNavScreens.WatchedMovies
    )

    // to show / hide bottom nav bar
    val showBottomBar =
        navController.currentBackStackEntryAsState().value?.destination?.route in bottomNavigationItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(
                    modifier = Modifier.height(56.dp),
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    bottomNavigationItems.forEachIndexed { index, screen ->
                        BottomNavigationItem(
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
                            },
                            unselectedContentColor = Color.DarkGray,
                            selectedContentColor = MaterialTheme.colors.primary
                        )
                    }

                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = BottomNavScreens.DiscoverScreen.route
            ) {
                composable(route = BottomNavScreens.DiscoverScreen.route) {
                    DiscoverScreen(onNavigate = { navController.navigate(route = it.route) })
                }
                composable(route = BottomNavScreens.SearchScreen.route) {
                    SearchScreen(onNavigate = { navController.navigate(route = it.route) })
                }
                composable(route = BottomNavScreens.WatchListScreen.route) {
                    WatchListScreen()
                }
                composable(route = BottomNavScreens.WatchedMovies.route) {
                    WatchedMoviesScreen(onNavigate = { navController.navigate(route = it.route) })
                }
                composable(
                    route = Screens.MovieProfile.route + "?id={id}",
                    arguments = listOf(
                        navArgument(name = "id") {
                            type = NavType.IntType
                            defaultValue = -1
                        })
                ) {
                    MovieProfileScreen(
                        onNavigate = { navController.navigate(route = it.route) },
                        onPopBackStack = { navController.popBackStack() })
                }
            }
        }
    }

}