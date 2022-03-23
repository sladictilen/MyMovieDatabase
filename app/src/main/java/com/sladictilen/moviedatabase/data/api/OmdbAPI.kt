package com.sladictilen.moviedatabase.data.api

import retrofit2.http.GET

interface OmdbAPI {
    @GET("s=")
    suspend fun searchForTitles(){

    }
}