package com.vadymex.movixapp.domain.use_case

import com.vadymex.movixapp.domain.model.movie.MoviesResponse
import com.vadymex.movixapp.domain.model.people.PeopleResponse
import com.vadymex.movixapp.domain.repository.PeopleRepository
import com.vadymex.movixapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor (private val repository: PeopleRepository) {
    operator fun invoke(): Flow<Resource<PeopleResponse>> = flow {
        try {
            emit(Resource.Loading())
            val people = repository.getPeople()
            if(people.isSuccessful){
                emit(Resource.Success(people.body()))
            }else{
                emit(Resource.Error(people.message() ?: "An unexpected error occured"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occured"))
        }
    }
}