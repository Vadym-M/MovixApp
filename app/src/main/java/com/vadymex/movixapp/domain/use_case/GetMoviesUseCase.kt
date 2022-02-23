package com.vadymex.movixapp.domain.use_case

import android.util.Log
import com.vadymex.movixapp.domain.model.movie.Movie
import com.vadymex.movixapp.domain.model.movie.MoviesResponse
import com.vadymex.movixapp.domain.repository.MovieRepository
import com.vadymex.movixapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<MoviesResponse>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getMovies()
            if(movies.isSuccessful){
                movies.body()?.forEach {it.summary = it.summary.replace("<.*?>".toRegex(), "")}
                emit(Resource.Success(movies.body()))
            }else{
                emit(Resource.Error(movies.message() ?: "An unexpected error occured"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occured"))
        }
    }
}
