package com.sladictilen.moviedatabase.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
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
    fun getWatchList(): Flow<List<ToWatchData>>

    @Query("SELECT * FROM WatchedData")
    fun getWatched(): Flow<List<WatchedData>>

    @Query("SELECT * FROM WatchedData WHERE id_movie = :id")
    fun getMovieFromWatchedListById(id: Int): WatchedData?

    @Query("SELECT * FROM ToWatchData WHERE id_movie = :id")
    suspend fun getMovieFromToWatchListById(id: Int): ToWatchData?
}