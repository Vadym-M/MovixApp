package com.vadymex.movixapp.data.repository

import com.vadymex.movixapp.data.remote.ApiService
import com.vadymex.movixapp.domain.repository.PeopleRepository
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val api: ApiService) : PeopleRepository{
    override suspend fun getPeople() = api.getPeople()
}