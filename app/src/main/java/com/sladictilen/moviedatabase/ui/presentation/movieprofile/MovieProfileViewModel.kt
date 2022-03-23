package com.sladictilen.moviedatabase.ui.presentation.movieprofile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val title by mutableStateOf(savedStateHandle.get<String>("title")!!)
}