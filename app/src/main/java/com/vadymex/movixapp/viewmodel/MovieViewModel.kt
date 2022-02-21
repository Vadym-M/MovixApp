package com.vadymex.movixapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadymex.movixapp.model.Movie
import com.vadymex.movixapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _response = MutableLiveData<List<Movie>>()
    val response :LiveData<List<Movie>>
    get() = _response

    init {
        fetchMovies()
    }

    private fun fetchMovies() = viewModelScope.launch {
        repository.getMovies().let { result ->
            if(result.isSuccessful){
                _response.postValue(result.body())
            }else{
                Log.d("tag", result.message())
            }
        }
    }
}