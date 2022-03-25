package com.sladictilen.moviedatabase.data.api

import com.sladictilen.moviedatabase.data.api.moviedetail.MovieDetailResponse
import com.sladictilen.moviedatabase.data.api.moviesearch.MoviesSearchResponse
import com.sladictilen.moviedatabase.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class MoviesRepository @Inject constructor(
    private val api: OmdbAPI
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
}