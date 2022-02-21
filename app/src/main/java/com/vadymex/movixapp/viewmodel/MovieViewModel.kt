package com.vadymex.movixapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadymex.movixapp.model.Movie
import com.vadymex.movixapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies :LiveData<List<Movie>>
    get() = _movies

    private val _moviesComedy = MutableLiveData<List<Movie>>()
    val moviesComedy :LiveData<List<Movie>>
        get() = _moviesComedy

    private val _moviesAction = MutableLiveData<List<Movie>>()
    val moviesAction :LiveData<List<Movie>>
        get() = _moviesAction

    private val _moviesCustom = MutableLiveData<List<Movie>>()
    val moviesCustom :LiveData<List<Movie>>
        get() = _moviesCustom

    init {
        fetchMovies()
        Log.d("tag", "test")
    }

    private fun fetchMovies() = viewModelScope.launch {
        repository.getMovies().let { result ->
            if(result.isSuccessful){
                val movies = result.body()
                movies?.let {
                    _movies.postValue(it.shuffled())
                    val comedy = async { separateByGenre(it, "Comedy") }.await()
                    val action = async { separateByGenre(it, "Action") }.await()
                    _moviesComedy.postValue(comedy)
                    _moviesAction.postValue(action)

                }

            }else{
                Log.d("tag", result.message())
            }
        }
    }

    private fun separateByGenre(movies: List<Movie>, genre: String): List<Movie> {
        return movies.filter { it.genres.contains(genre)}.shuffled().slice(0..9)
    }

    fun getAllByGenre(genre: String){
        val newMoviesList = movies.value
            ?.filter{ it.genres.contains(genre)}
            ?.shuffled()
            .let {_moviesCustom.postValue(it)}
    }
}