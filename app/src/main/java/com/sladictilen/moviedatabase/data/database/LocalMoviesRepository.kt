package com.sladictilen.moviedatabase.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface LocalMoviesRepository {
    suspend fun addToWatchList(toWatchData: ToWatchData)

    suspend fun addToWatched(watchedData: WatchedData)

    suspend fun deleteWatchListMovie(toWatchData: ToWatchData)

    suspend fun deleteWatchedMovie(watchedData: WatchedData)

    fun getWatchList() : Flow<List<ToWatchData>>

    fun getWatched() : Flow<List<WatchedData>>
}