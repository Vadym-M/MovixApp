package com.vadymex.movixapp.domain.repository

import com.vadymex.movixapp.domain.model.people.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET

interface PeopleRepository {

    suspend fun getPeople(): Response<PeopleResponse>
}