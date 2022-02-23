package com.vadymex.movixapp.presentation.utils

sealed class OnItemShow(private val data: String? = null){
    class ShowMovie(val data: String): OnItemShow(data)
    class ShowMovies(): OnItemShow()
    class ShowPerson(val data: String): OnItemShow(data)
    class ShowPeople(): OnItemShow()
}
