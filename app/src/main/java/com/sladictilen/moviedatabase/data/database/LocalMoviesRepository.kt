package com.sladictilen.moviedatabase.data.database

import kotlinx.coroutines.flow.Flow

interface LocalMoviesRepository {
    suspend fun addToWatchList(toWatchData: ToWatchData)

    suspend fun addToWatched(watchedData: WatchedData)

    suspend fun deleteWatchListMovie(toWatchData: ToWatchData)

    suspend fun deleteWatchedMovie(watchedData: WatchedData)

    fun getWatchList() : Flow<List<ToWatchData>>

    fun getWatched() : Flow<List<WatchedData>>

    fun getMovieFromWatchedListById(id: Int): WatchedData?

    fun getMovieFromToWatchListById(id: Int): ToWatchData?
}