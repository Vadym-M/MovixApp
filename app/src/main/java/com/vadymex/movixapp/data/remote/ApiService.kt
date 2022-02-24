package com.vadymex.movixapp.data.remote

import com.vadymex.movixapp.domain.model.movie.Movie
import com.vadymex.movixapp.domain.model.movie.MoviesResponse
import com.vadymex.movixapp.domain.model.movie.cast.CastResponse
import com.vadymex.movixapp.domain.model.movie.episodes.EpisodesResponse
import com.vadymex.movixapp.domain.model.movie.seasons.SeasonsResponse
import com.vadymex.movixapp.domain.model.people.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("shows")
    suspend fun getMovies(): Response<MoviesResponse>

    @GET("shows/{id}")
    suspend fun getMovie(@Path("id") id: String): Response<Movie>

    @GET("shows/{id}/seasons")
    suspend fun getSeasonsListOfMovie(@Path("id") id:String): Response<SeasonsResponse>

    @GET("seasons/{id}/episodes")
    suspend fun getEpisodesListById(@Path("id") id:String): Response<EpisodesResponse>

    @GET("people")
    suspend fun getPeople(): Response<PeopleResponse>

    @GET("people/{id}")
    suspend fun getPersonById(@Path("id") id:String)

    @GET("shows/{id}/cast")
    suspend fun getCastForMovie(@Path("id") id:String): Response<CastResponse>

}