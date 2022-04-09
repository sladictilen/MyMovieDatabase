package com.sladictilen.moviedatabase.data.apiOMDB

import com.sladictilen.moviedatabase.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class RatingsRepository @Inject constructor(
    private val api: OmdbAPI
) {
    suspend fun getRatings(imdbId: String): Resource<OmdbMovieResponse> {
        val response = try {
            api.getRatings(imdbId)
        } catch (e: Exception) {
            return Resource.Error("Error getting Ratings. -> $e")
        }
        return Resource.Success(response)
    }
}