package com.sladictilen.moviedatabase.util

sealed class WatchStatus {
    object Status{
        var NotWatched: String = "Not Watched"
    }

    //var Watched: String = "Watched"
    var NotWatched: String = "Not Watched"
    var OnWatchList: String = "On your To-Watch List"

}
