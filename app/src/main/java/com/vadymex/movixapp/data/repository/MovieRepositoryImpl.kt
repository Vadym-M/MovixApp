package com.vadymex.movixapp.data.repository

import com.vadymex.movixapp.data.remote.ApiService
import com.vadymex.movixapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository{
    override suspend fun  getMovies() = apiService.getMovies()
    override suspend fun getMovie(movieId: String) = apiService.getMovie(movieId)
}