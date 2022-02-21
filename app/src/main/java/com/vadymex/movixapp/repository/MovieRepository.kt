package com.vadymex.movixapp.repository

import com.vadymex.movixapp.api.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun  getMovies() = apiService.getMovies()
}