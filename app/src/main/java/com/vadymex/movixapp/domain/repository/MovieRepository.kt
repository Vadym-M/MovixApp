package com.vadymex.movixapp.domain.repository

import com.vadymex.movixapp.domain.model.movie.Movie
import com.vadymex.movixapp.domain.model.movie.MoviesResponse
import com.vadymex.movixapp.domain.model.movie.cast.CastResponse
import com.vadymex.movixapp.domain.model.movie.episodes.EpisodesResponse
import com.vadymex.movixapp.domain.model.movie.seasons.SeasonsResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getMovies(): Response<MoviesResponse>

    suspend fun getMovie(movieId: String): Response<Movie>

    suspend fun getSeasons(movieId: String): Response<SeasonsResponse>

    suspend fun getEpisodes(seasonId: String): Response<EpisodesResponse>

    suspend fun getCast(movieId: String): Response<CastResponse>

}