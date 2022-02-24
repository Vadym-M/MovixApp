package com.vadymex.movixapp.domain.use_case

import com.vadymex.movixapp.domain.model.movie.episodes.EpisodesResponse
import com.vadymex.movixapp.domain.repository.MovieRepository
import com.vadymex.movixapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetMovieEpisodesUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(seasonId: String): Flow<Resource<EpisodesResponse>> = flow {
        try {
            emit(Resource.Loading())
            val episodes = repository.getEpisodes(seasonId)
            if (episodes.isSuccessful){
                emit(Resource.Success(episodes.body()))
            }else{
                emit(Resource.Error(episodes.message() ?: "An unexpected error occured"))
            }
        }catch (e: Exception){
            emit(Resource.Error(e.message ?: "An unexpected error occured"))
        }
    }
}