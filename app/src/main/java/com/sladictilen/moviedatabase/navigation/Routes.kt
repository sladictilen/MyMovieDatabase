package com.sladictilen.moviedatabase.navigation

sealed class Routes(val route: String){
    object WhatToWatchScreen : Routes("what_to_watch_screen")
}
