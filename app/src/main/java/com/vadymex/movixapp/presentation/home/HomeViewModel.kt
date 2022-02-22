package com.vadymex.movixapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadymex.movixapp.domain.model.movie.Movie
import com.vadymex.movixapp.domain.model.people.Person
import com.vadymex.movixapp.domain.use_case.GetMoviesUseCase
import com.vadymex.movixapp.domain.use_case.GetPeopleUseCase
import com.vadymex.movixapp.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCaseGetMovies: GetMoviesUseCase,
    private val useCaseGetPeople: GetPeopleUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies :LiveData<List<Movie>> = _movies

    private val _people = MutableLiveData<List<Person>>()
    val people :LiveData<List<Person>> = _people

    private val _progressBarVisible = MutableLiveData<Boolean>()
    val progressBarVisible: LiveData<Boolean> = _progressBarVisible

    private val _showError = MutableLiveData<String>()
    val showError: LiveData<String> = _showError

    private var listMovies = listOf<Movie>()
    private var listPeople = listOf<Person>()



    init {
        getMovies()
        getPeople()
    }

    private fun getMovies(){
        useCaseGetMovies().onEach { result ->
            when(result){
                is Resource.Success -> {
                    listMovies = result.data ?: emptyList()
                    val shortList = listMovies.shuffled().slice(0..9)
                    _movies.postValue(shortList)
                }
                is Resource.Error -> {_showError.postValue(result.msg ?: "An unexpected error occured")}
                is Resource.Loading -> {_progressBarVisible.postValue(true)}
            }
        }.launchIn(viewModelScope)
    }
    private fun getPeople(){
        useCaseGetPeople().onEach { result ->
            when(result){
                is Resource.Success -> {
                    listPeople = result.data ?: emptyList()
                    val shortList = listPeople.shuffled().slice(0..9)
                    _people.postValue(shortList)
                }
                is Resource.Error -> {_showError.postValue(result.msg ?: "An unexpected error occured")}
                is Resource.Loading -> {_progressBarVisible.postValue(true)}
            }
        }.launchIn(viewModelScope)
    }



}