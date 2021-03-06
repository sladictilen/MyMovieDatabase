package com.sladictilen.moviedatabase.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchedData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val id_movie: Int,
    val title: String,
    val genre: String,
    val year: String,
    val runtime: Int,
    val imdbRating: Double,
    val tomatoRating: String,
    val posterUrl: String,
    val dateWatched: String,
    val userRating: String
)
