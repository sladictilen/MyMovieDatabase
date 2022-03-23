package com.sladictilen.moviedatabase.navigation

sealed class Screens(
    val route: String
) {
    object MovieProfile : Screens("movieprofile")
}