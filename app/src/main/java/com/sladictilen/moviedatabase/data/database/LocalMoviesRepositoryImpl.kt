package com.sladictilen.moviedatabase.data.database

import kotlinx.coroutines.flow.Flow

class LocalMoviesRepositoryImpl(
    private val dao: DatabaseDao
) : LocalMoviesRepository {
    override suspend fun addToWatchList(toWatchData: ToWatchData) {
        dao.addToWatchList(toWatchData)
    }

    override suspend fun addToWatched(watchedData: WatchedData) {
        dao.addToWatched(watchedData)
    }

    override suspend fun deleteWatchListMovie(toWatchData: ToWatchData) {
        dao.deleteWatchListMovie(toWatchData)
    }

    override suspend fun deleteWatchedMovie(watchedData: WatchedData) {
        dao.deleteWatchedMovie(watchedData)
    }

    override fun getWatchList(): Flow<List<ToWatchData>> {
        return dao.getWatchList()
    }

    override fun getWatched(): Flow<List<WatchedData>> {
        return dao.getWatched()
    }
}