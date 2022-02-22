package com.vadymex.movixapp.di

import com.vadymex.movixapp.data.remote.ApiService
import com.vadymex.movixapp.data.repository.MovieRepositoryImpl
import com.vadymex.movixapp.data.repository.PeopleRepositoryImpl
import com.vadymex.movixapp.domain.repository.MovieRepository
import com.vadymex.movixapp.domain.repository.PeopleRepository
import com.vadymex.movixapp.presentation.utils.Constants.Companion.BASE_URL
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

    @Provides
    fun provideBaseUrl () = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideMovieRepository(api: ApiService): MovieRepository{
        return MovieRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun providePeopleRepository(api: ApiService): PeopleRepository {
        return PeopleRepositoryImpl(api)
    }
}