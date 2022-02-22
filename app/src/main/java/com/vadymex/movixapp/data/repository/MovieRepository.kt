package com.vadymex.movixapp.data.repository

import com.vadymex.movixapp.data.remote.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun  getMovies() = apiService.getMovies()
}