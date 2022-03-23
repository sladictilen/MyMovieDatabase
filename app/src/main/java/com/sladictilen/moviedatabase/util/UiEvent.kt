package com.sladictilen.moviedatabase.util

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    object PopBackStack : UiEvent()
}