package com.sladictilen.moviedatabase.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchedData(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val id_movie: Int?,
    val title: String,
    val genre: String,
    val imdbRating: Double,
    val tomatoRating: Int,
    val posterUrl: String,
)
