package com.sladictilen.moviedatabase.data.api

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
            return Resource.Error("An Unknown error occured.")
        }
        return Resource.Success(response)
    }
}