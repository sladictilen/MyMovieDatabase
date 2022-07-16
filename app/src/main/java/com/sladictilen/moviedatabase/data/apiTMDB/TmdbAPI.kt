package com.sladictilen.moviedatabase.data.apiTMDB


import com.sladictilen.moviedatabase.data.apiTMDB.cast.CastResponse
import com.sladictilen.moviedatabase.data.apiTMDB.featuredmovies.TrendingWeeklyMoviesResponse
import com.sladictilen.moviedatabase.data.apiTMDB.moviedetail.MovieDetailResponse
import com.sladictilen.moviedatabase.data.apiTMDB.moviesearch.MoviesSearchResponse
import com.sladictilen.moviedatabase.data.apiTMDB.movietrailer.MovieTrailerData
import com.sladictilen.moviedatabase.data.apiTMDB.movietrailer.MovieTrailerResponse
import com.sladictilen.moviedatabase.data.apiTMDB.popularmovies.PopularMoviesResponse
import com.sladictilen.moviedatabase.data.apiTMDB.similarmovies.SimilarMoviesResponse
import com.sladictilen.moviedatabase.util.Constants.Companion.TMDB_API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("api_key") apiKey: String = TMDB_API_KEY
    ): MoviesSearchResponse

    @GET("movie/{id}")
    suspend fun getIdDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = TMDB_API_KEY
    ): MovieDetailResponse

    @GET("movie/{id}/credits")
    suspend fun getCast(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = TMDB_API_KEY
    ): CastResponse

    @GET("movie/{id}/similar")
    suspend fun getSimilarMovies(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = TMDB_API_KEY
    ): SimilarMoviesResponse

    @GET("trending/movie/week")
    suspend fun getTrendingWeeklyMovies(
        @Query("api_key") apiKey: String = TMDB_API_KEY
    ): TrendingWeeklyMoviesResponse


    /*TODO ? */
    @GET("trending/movie/day")
    suspend fun getTrendingDailyMovies(
        @Query("api_key") apiKey: String = TMDB_API_KEY
    ): TrendingWeeklyMoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = TMDB_API_KEY
    ): PopularMoviesResponse

    @GET("movie/{id}/videos")
    suspend fun getMovieTrailer(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = TMDB_API_KEY
    ): MovieTrailerResponse
}