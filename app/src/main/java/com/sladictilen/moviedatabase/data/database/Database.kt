package com.sladictilen.moviedatabase.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ToWatchData::class, WatchedData::class],
    version = 9
)
abstract class Database : RoomDatabase() {
    abstract val dao: DatabaseDao
}