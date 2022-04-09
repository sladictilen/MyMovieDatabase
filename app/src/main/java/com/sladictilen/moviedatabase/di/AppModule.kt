package com.sladictilen.moviedatabase.di


import android.app.Application
import androidx.room.Room
import com.sladictilen.moviedatabase.data.apiOMDB.OmdbAPI
import com.sladictilen.moviedatabase.data.apiOMDB.RatingsRepository
import com.sladictilen.moviedatabase.data.apiTMDB.MoviesRepository
import com.sladictilen.moviedatabase.data.apiTMDB.TmdbAPI
import com.sladictilen.moviedatabase.data.database.Database
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepository
import com.sladictilen.moviedatabase.data.database.LocalMoviesRepositoryImpl
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
        api: TmdbAPI
    ) = MoviesRepository(api)

    @Singleton
    @Provides
    fun provideTmdbApi(): TmdbAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRatingsRepository(
        api: OmdbAPI
    ) = RatingsRepository(api)

    @Singleton
    @Provides
    fun provideOmdbApi(): OmdbAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.OMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "local_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(db: Database): LocalMoviesRepository {
        return LocalMoviesRepositoryImpl(dao = db.dao)
    }

}