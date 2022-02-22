package com.vadymex.movixapp.data.remote

import com.vadymex.movixapp.domain.model.movie.Movie
import com.vadymex.movixapp.domain.model.movie.MoviesResponse
import com.vadymex.movixapp.domain.model.people.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("shows")
    suspend fun getMovies(): Response<MoviesResponse>

    @GET("shows/{id}")
    suspend fun getMovie(@Path("id") movieId: String): Response<Movie>

    @GET("people")
    suspend fun getPeople(): Response<PeopleResponse>

}