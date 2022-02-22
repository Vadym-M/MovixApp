package com.vadymex.movixapp.data.remote

import com.vadymex.movixapp.domain.model.MoviesResponse
import com.vadymex.movixapp.presentation.utils.Constants.Companion.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getMovies():Response<MoviesResponse>
}