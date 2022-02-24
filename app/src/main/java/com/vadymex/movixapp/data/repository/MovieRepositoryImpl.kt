package com.vadymex.movixapp.data.repository

import com.vadymex.movixapp.data.remote.ApiService
import com.vadymex.movixapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository{
    override suspend fun  getMovies() = apiService.getMovies()
    override suspend fun getMovie(movieId: String) = apiService.getMovie(movieId)
    override suspend fun getSeasons(movieId: String) = apiService.getSeasonsListOfMovie(movieId)
    override suspend fun getEpisodes(seasonId: String) = apiService.getEpisodesListById(seasonId)
    override suspend fun getCast(movieId: String) = apiService.getCastForMovie(movieId)
}