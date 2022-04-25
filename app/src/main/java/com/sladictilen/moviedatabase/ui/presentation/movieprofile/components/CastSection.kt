package com.sladictilen.moviedatabase.ui.presentation.movieprofile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sladictilen.moviedatabase.data.apiTMDB.cast.Cast

@Composable
fun CastSection(cast: List<Cast>) {
    // Data from MovieProfileViewModel
    LazyRow(Modifier.wrapContentSize()) {
        items(cast) {
            ActorItem(
                name = it.name,
                characterName = it.character,
                profileImg = it.profile_path
            )
        }
    }
}