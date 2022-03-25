package com.sladictilen.moviedatabase.data.api


import com.sladictilen.moviedatabase.data.api.moviedetail.MovieDetailResponse
import com.sladictilen.moviedatabase.data.api.moviesearch.MoviesSearchResponse
import com.sladictilen.moviedatabase.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OmdbAPI {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("api_key") apiKey: String = API_KEY
    ): MoviesSearchResponse

    @GET("movie/{id}")
    suspend fun getIdDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetailResponse
}