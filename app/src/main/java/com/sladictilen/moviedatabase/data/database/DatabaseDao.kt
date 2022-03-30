package com.sladictilen.moviedatabase.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchList(toWatchData: ToWatchData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatched(watchedData: WatchedData)

    @Delete
    suspend fun deleteWatchListMovie(toWatchData: ToWatchData)

    @Delete
    suspend fun deleteWatchedMovie(watchedData: WatchedData)

    @Query("SELECT * FROM ToWatchData")
    fun getWatchList() : Flow<List<ToWatchData>>

    @Query("SELECT * FROM WatchedData")
    fun getWatched() : Flow<List<WatchedData>>
}