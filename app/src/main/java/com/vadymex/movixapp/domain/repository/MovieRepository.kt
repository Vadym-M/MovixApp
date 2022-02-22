package com.vadymex.movixapp.domain.repository

import com.vadymex.movixapp.domain.model.movie.Movie
import com.vadymex.movixapp.domain.model.movie.MoviesResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getMovies(): Response<MoviesResponse>

    suspend fun getMovie(movieId: String): Response<Movie>

}