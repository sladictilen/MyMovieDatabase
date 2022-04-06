package com.sladictilen.moviedatabase.data.api


import com.sladictilen.moviedatabase.data.api.cast.CastResponse
import com.sladictilen.moviedatabase.data.api.featuredmovies.TrendingWeeklyMoviesResponse
import com.sladictilen.moviedatabase.data.api.moviedetail.MovieDetailResponse
import com.sladictilen.moviedatabase.data.api.moviesearch.MoviesSearchResponse
import com.sladictilen.moviedatabase.data.api.similarmovies.SimilarMoviesResponse
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

    @GET("movie/{id}/credits")
    suspend fun getCast(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): CastResponse

    @GET("movie/{id}/similar")
    suspend fun getSimilarMovies(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): SimilarMoviesResponse

    @GET("trending/movie/week")
    suspend fun getTrendingWeeklyMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): TrendingWeeklyMoviesResponse


    /*TODO ? */
    @GET("trending/movie/day")
    suspend fun getTrendingDailyMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): TrendingWeeklyMoviesResponse
}