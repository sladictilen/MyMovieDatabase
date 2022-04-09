package com.sladictilen.moviedatabase.data.apiOMDB

import android.util.Log
import retrofit2.http.GET
import retrofit2.http.Query
import com.sladictilen.moviedatabase.util.Constants.Companion.OMDB_API_KEY
import retrofit2.http.Path

// For getting RottenTomatoes and IMDB rating.
// http://www.omdbapi.com/?i=tt3896198&apikey=938244be

interface OmdbAPI {

    @GET(".")
    suspend fun getRatings(
        @Query("i") imdbId: String,
        @Query("apikey") api: String = OMDB_API_KEY
    ): OmdbMovieResponse
}