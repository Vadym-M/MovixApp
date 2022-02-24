package com.vadymex.movixapp.domain.use_case

import com.vadymex.movixapp.domain.model.movie.Movie
import com.vadymex.movixapp.domain.repository.MovieRepository
import com.vadymex.movixapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(movieId: String): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getMovie(movieId)
            if (movie.isSuccessful){
                emit(Resource.Success(movie.body()))
            }else{
                emit(Resource.Error(movie.message() ?: "An unexpected error occured"))
            }
        }catch (e: Exception){
            emit(Resource.Error(e.message ?: "An unexpected error occured"))
        }
    }
}