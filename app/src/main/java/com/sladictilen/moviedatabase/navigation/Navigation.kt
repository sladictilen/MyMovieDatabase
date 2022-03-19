package com.sladictilen.moviedatabase.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sladictilen.moviedatabase.ui.presentation.discover.DiscoverScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navItems = listOf<>()
    Scaffold(
        bottomBar = {

        }
    ) {
        NavHost(navController = navController, startDestination = Routes.WhatToWatchScreen.route) {
            composable(route = Routes.WhatToWatchScreen.route) {
                DiscoverScreen()
            }
        }
    }

}