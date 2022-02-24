package com.vadymex.movixapp.domain.use_case

import com.vadymex.movixapp.domain.model.movie.cast.CastResponse
import com.vadymex.movixapp.domain.repository.MovieRepository
import com.vadymex.movixapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCastUseCase @Inject constructor (private val repository: MovieRepository) {
    operator fun invoke(movieId: String): Flow<Resource<CastResponse>> = flow {
        try {
            emit(Resource.Loading())
            val cast = repository.getCast(movieId)
            if(cast.isSuccessful){
                emit(Resource.Success(cast.body()))
            }else{
                emit(Resource.Error(cast.message() ?: "An unexpected error occured"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occured"))
        }
    }
}