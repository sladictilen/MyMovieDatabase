package com.sladictilen.moviedatabase.data.apiOMDB

import retrofit2.http.GET
import retrofit2.http.Query
import com.sladictilen.moviedatabase.util.Constants.Companion.OMDB_API_KEY

// For getting RottenTomatoes and IMDB rating.

interface OmdbAPI {
    @GET("")
    suspend fun getImdbRating(
        @Query("i") imdbId: String,
        @Query("apikey") api: String = OMDB_API_KEY
    ): OmdbMovieResponse
}