package com.sladictilen.moviedatabase.data.apiTMDB

import com.sladictilen.moviedatabase.data.apiTMDB.cast.CastResponse
import com.sladictilen.moviedatabase.data.apiTMDB.featuredmovies.TrendingWeeklyMoviesResponse
import com.sladictilen.moviedatabase.data.apiTMDB.moviedetail.MovieDetailResponse
import com.sladictilen.moviedatabase.data.apiTMDB.moviesearch.MoviesSearchResponse
import com.sladictilen.moviedatabase.data.apiTMDB.movietrailer.MovieTrailerData
import com.sladictilen.moviedatabase.data.apiTMDB.movietrailer.MovieTrailerResponse
import com.sladictilen.moviedatabase.data.apiTMDB.popularmovies.PopularMoviesResponse
import com.sladictilen.moviedatabase.data.apiTMDB.similarmovies.SimilarMoviesResponse
import com.sladictilen.moviedatabase.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class MoviesRepository @Inject constructor(
    private val api: TmdbAPI
) {
    suspend fun searchMovies(title: String, page: Int): Resource<MoviesSearchResponse> {
        val response = try {
            api.searchMovies(title, page)
        } catch (e: Exception) {
            return Resource.Error("An Unknown error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getIdDetails(id: Int): Resource<MovieDetailResponse> {
        val response = try {
            api.getIdDetails(id)
        } catch (e: Exception) {
            return Resource.Error("Error getting movie details.")
        }
        return Resource.Success(response)
    }

    suspend fun getMovieCast(id: Int): Resource<CastResponse> {
        val response = try {
            api.getCast(id)
        } catch (e: Exception) {
            return Resource.Error("Error getting movie cast.")
        }
        return Resource.Success(response)
    }

    suspend fun getSimilarMovies(id: Int): Resource<SimilarMoviesResponse> {
        val response = try {
            api.getSimilarMovies(id)
        } catch (e: Exception) {
            return Resource.Error("Error getting similar movies.")
        }
        return Resource.Success(response)
    }

    suspend fun getTrendingWeeklyMovies(): Resource<TrendingWeeklyMoviesResponse> {
        val response = try {
            api.getTrendingWeeklyMovies()
        } catch (e: Exception) {
            return Resource.Error("Error getting Weekly trending movies.")
        }
        return Resource.Success(response)
    }

    suspend fun getPopularMovies(): Resource<PopularMoviesResponse> {
        val response = try {
            api.getPopularMovies()
        } catch (e: Exception) {
            return Resource.Error("Error getting Popular movies.")
        }
        return Resource.Success(response)
    }

    suspend fun getYTTrailer(id: Int): Resource<MovieTrailerResponse> {
        val response = try {
            api.getMovieTrailer(id)
        } catch (e: Exception) {
            return Resource.Error("Error getting movie trailer.")
        }
        return Resource.Success(response)
    }
}