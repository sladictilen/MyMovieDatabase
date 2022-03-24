package com.sladictilen.moviedatabase.di


import com.sladictilen.moviedatabase.data.api.MoviesRepository
import com.sladictilen.moviedatabase.data.api.OmdbAPI
import com.sladictilen.moviedatabase.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        api: OmdbAPI
    ) = MoviesRepository(api)

    @Singleton
    @Provides
    fun provideOmdbApi(): OmdbAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbAPI::class.java)
    }

}