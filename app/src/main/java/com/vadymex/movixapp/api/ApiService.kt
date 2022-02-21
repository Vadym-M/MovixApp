package com.vadymex.movixapp.api

import com.vadymex.movixapp.model.MoviesResponse
import com.vadymex.movixapp.utils.Constants.Companion.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getMovies():Response<MoviesResponse>
}