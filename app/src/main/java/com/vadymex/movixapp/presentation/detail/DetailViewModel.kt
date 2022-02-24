package com.vadymex.movixapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadymex.movixapp.domain.model.movie.Movie
import com.vadymex.movixapp.domain.model.movie.cast.CastItem
import com.vadymex.movixapp.domain.model.movie.episodes.Episode
import com.vadymex.movixapp.domain.model.movie.seasons.Season
import com.vadymex.movixapp.domain.use_case.GetCastUseCase
import com.vadymex.movixapp.domain.use_case.GetMovieEpisodesUseCase
import com.vadymex.movixapp.domain.use_case.GetMovieSeasonsUseCase
import com.vadymex.movixapp.domain.use_case.GetMovieUseCase
import com.vadymex.movixapp.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCastUseCase: GetCastUseCase,
    private val getMovieEpisodesUseCase: GetMovieEpisodesUseCase,
    private val getMovieSeasonsUseCase: GetMovieSeasonsUseCase,
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {
private lateinit var movieId: String

    private val _movie = MutableLiveData<Movie>()
    val movie : LiveData<Movie> = _movie

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes :LiveData<List<Episode>> = _episodes

    private val _seasons = MutableLiveData<List<Season>>()
    val seasons :LiveData<List<Season>> = _seasons

    private val _cast = MutableLiveData<List<CastItem>>()
    val cast :LiveData<List<CastItem>> = _cast

    private val _progressBarVisible = MutableLiveData<Boolean>()
    val progressBarVisible: LiveData<Boolean> = _progressBarVisible

    private val _showError = MutableLiveData<String>()
    val showError: LiveData<String> = _showError

    fun initMovieId(mId: String){
        movieId = mId
        getMovie()
        getSeasons()
        getCast()
    }
    private fun getMovie(){
        getMovieUseCase(movieId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    val movie = result.data
                    movie?.let { _movie.postValue(it) }
                }
                is Resource.Error -> {_showError.postValue(result.msg ?: "An unexpected error occured")}
                is Resource.Loading -> {_progressBarVisible.postValue(true)}
            }
        }.launchIn(viewModelScope)
    }
    private fun getSeasons(){
        getMovieSeasonsUseCase(movieId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    val seasons = result.data ?: emptyList()
                    _seasons.postValue(seasons)
                }
                is Resource.Error -> {_showError.postValue(result.msg ?: "An unexpected error occured")}
                is Resource.Loading -> {_progressBarVisible.postValue(true)}
            }
        }.launchIn(viewModelScope)
    }
    private fun getEpisodes(seasonId:String){
        getMovieEpisodesUseCase(seasonId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    val episodes = result.data ?: emptyList()
                     _episodes.postValue(episodes)
                }
                is Resource.Error -> {_showError.postValue(result.msg ?: "An unexpected error occured")}
                is Resource.Loading -> {_progressBarVisible.postValue(true)}
            }
        }.launchIn(viewModelScope)
    }
    private fun getCast(){
        getCastUseCase(movieId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    val cast = result.data ?: emptyList()
                    _cast.postValue(cast)
                }
                is Resource.Error -> {_showError.postValue(result.msg ?: "An unexpected error occured")}
                is Resource.Loading -> {_progressBarVisible.postValue(true)}
            }
        }.launchIn(viewModelScope)
    }
}